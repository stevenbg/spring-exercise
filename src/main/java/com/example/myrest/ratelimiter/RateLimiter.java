package com.example.myrest.ratelimiter;

import io.github.bucket4j.*;
import io.github.bucket4j.local.LocalBucketBuilder;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A generic rate limiter. Requires a CacheManager.
 */
public class RateLimiter {
//    To change the params on the fly,
//    we'd have to replaceConfiguration for all the buckets,
//    while moving them in a new cache with the new expiry.
//    We're using Cache, so the entries can expire
//    or we'd have to write a gc to prevent the ConcurrentHashMap from growing indefinitely
    private final Cache<String, Bucket> cache;
    private LocalBucketBuilder localBucketBuilder;

    static private final AtomicInteger instanceCounter = new AtomicInteger(0);

    /**
     * @param rate number of requests
     * @param interval period in seconds
     * @param cacheManager the app's CacheManager
     */

    public RateLimiter(Integer rate, Integer interval, CacheManager cacheManager) {
//        keeping stale buckets past interval is pointless
        MutableConfiguration<String, Bucket> configuration =
                new MutableConfiguration<String, Bucket>()
                        .setTypes(String.class, Bucket.class)
                        .setStoreByValue(false)
                        .setStatisticsEnabled(false)
                        .setExpiryPolicyFactory(
                                AccessedExpiryPolicy.factoryOf(new javax.cache.expiry.Duration(TimeUnit.SECONDS, interval)));

        String cache_name = getClass().getCanonicalName() + String.valueOf(instanceCounter.getAndIncrement());
        cache = cacheManager.createCache(cache_name, configuration);

        Refill refill = Refill.intervally(rate, Duration.ofSeconds(interval));
        Bandwidth limit = Bandwidth.classic(rate, refill);
        localBucketBuilder = Bucket4j.builder().addLimit(limit);
    }

    public RateLimiter(Integer rate, Integer interval, CacheManager cacheManager, TimeMeter customTimeMeter) {
        this(rate, interval, cacheManager);
        localBucketBuilder = localBucketBuilder.withCustomTimePrecision(customTimeMeter);
    }

    public Result attemptAction(String actor_key) {
//        wasteful compared to computeIfAbsent
        cache.putIfAbsent(actor_key, localBucketBuilder.build());
        Bucket tokenBucket = cache.get(actor_key);

        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);

        return new Result(probe.isConsumed(), probe.getRemainingTokens(), probe.getNanosToWaitForRefill() / 1_000_000_000);
    }

    static class Result {
        public final Boolean success;
        public final Long remaining;
        public final Long wait;

        public Result(Boolean success, Long remaining, Long wait) {
            this.success = success;
            this.remaining = remaining;
            this.wait = wait;
        }
    }

    public void finalize() {
        cache.getCacheManager().destroyCache(cache.getName());
    }

}
