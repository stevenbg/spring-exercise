package com.example.myrest.ratelimiter;

import io.github.bucket4j.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import javax.cache.spi.CachingProvider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
// todo extract RateLimiter
/**
 * Fat interceptor
 */
public class RateLimiterInterceptor implements HandlerInterceptor {

//    to change the params on the fly,
//    we'd have to replaceConfiguration for all the buckets,
//    while moving them in a new cache with the new expiry
    private final Integer rate;
    private final Integer interval;
//    we're using Cache, so the entries can expire
//    or we'd have to write a gc to prevent the ConcurrentHashMap from growing indefinitely
    private Cache<String, Bucket> cache;

    static private int instanceCounter = 0;

    /**
     * Returns a rate limiting interceptor
     * @param rate number of requests
     * @param interval period in seconds
     */
    public RateLimiterInterceptor(Integer rate, Integer interval) {
        this.rate = rate;
        this.interval = interval;

//        keeping stale buckets past interval is pointless
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration<String, Bucket> configuration =
                new MutableConfiguration<String, Bucket>()
                        .setTypes(String.class, Bucket.class)
                        .setStoreByValue(false)
                        .setStatisticsEnabled(false)
                        .setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new javax.cache.expiry.Duration(TimeUnit.SECONDS, interval)));

        cache = cacheManager.createCache("ratelimiter" + instanceCounter++, configuration);
    }

    private Bucket makeBucket(String key) {
        Refill refill = Refill.intervally(rate, Duration.ofSeconds(interval));
        Bandwidth limit = Bandwidth.classic(rate, refill);
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        should cover X-Forwarded-For
        String key = request.getRemoteAddr();
//        wasteful compared to computeIfAbsent
        cache.putIfAbsent(key, makeBucket(key));
        Bucket tokenBucket = cache.get(key);

        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        }
        else {
            Long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));

            throw new RateLimiterException("Exhausted API quota for " + key);
        }
    }

}
