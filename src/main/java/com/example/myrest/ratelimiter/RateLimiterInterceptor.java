package com.example.myrest.ratelimiter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.cache.CacheManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RateLimiterInterceptor implements HandlerInterceptor {

    private final RateLimiter rateLimiter;

    /**
     * Returns a rate limiting interceptor
     * @param rate number of requests
     * @param interval period in seconds
     * @param cacheManager the app CacheManager
     */
    public RateLimiterInterceptor(Integer rate, Integer interval, CacheManager cacheManager) {
        rateLimiter = new RateLimiter(rate, interval, cacheManager);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        should cover X-Forwarded-For
        String key = request.getRemoteAddr();
        RateLimiter.Result test = rateLimiter.attemptAction(key);

        if (test.success) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(test.remaining));
            return true;
        }
        else {
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(test.wait));
            throw new RateLimiterException("Exhausted API quota for " + key);
        }
    }

}
