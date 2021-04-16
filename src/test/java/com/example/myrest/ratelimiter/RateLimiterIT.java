package com.example.myrest.ratelimiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.cache.Caching;

import static org.junit.jupiter.api.Assertions.*;

class RateLimiterIT {
    private static final Long RATE = 5L;
    private static final Long INTERVAL = 10L;
    private static final String KEY = "key";
    private RateLimiter rateLimiter;
    private TimeMeterMock timeMeterMock;

    @BeforeEach
    void setUp() {
        timeMeterMock = new TimeMeterMock();
        rateLimiter = new RateLimiter(RATE.intValue(), INTERVAL.intValue(), Caching.getCachingProvider().getCacheManager(), timeMeterMock);
    }

    private void attemptActionNTimes(Long times) {
        for (int i = 0; i < times; i++) {
            rateLimiter.attemptAction(KEY);
        }
    }

    @Test
    void attemptActionAndGetProperRemaining() {
        RateLimiter.Result result = rateLimiter.attemptAction(KEY);

        assertEquals(RATE - 1, result.remaining, "Wrong remaining count");
    }

    @Test
    void attemptActionAboveLimitAndGetProperWait() {
        attemptActionNTimes(RATE);

        RateLimiter.Result result = rateLimiter.attemptAction(KEY);

        assertEquals(INTERVAL, result.wait, "Wrong wait time (without tick)");
    }

    @Test
    void exhaustAttemptsThenTickAndGetProperWait() {
        attemptActionNTimes(RATE);
        timeMeterMock.addSeconds(1);

        RateLimiter.Result result = rateLimiter.attemptAction(KEY);

        assertEquals(INTERVAL - 1, result.wait, "Wrong wait time (with tick)");
    }

    @Test
    void attemptActionUpToRateLimitAndSucceed() {
        attemptActionNTimes(RATE - 1);

        RateLimiter.Result result = rateLimiter.attemptAction(KEY);

        assertTrue(result.success, "Denied an action within limits");
    }

    @Test
    void attemptActionAboveRateLimitAndFail() {
        attemptActionNTimes(RATE);

        RateLimiter.Result result = rateLimiter.attemptAction(KEY);

        assertFalse(result.success, "Allowed an action past limits");
    }

    @Test
    void exhaustAttemptsThenReplenishAndSucceed() {
        attemptActionNTimes(RATE);
        timeMeterMock.addSeconds(INTERVAL);

        RateLimiter.Result result = rateLimiter.attemptAction(KEY);

        assertTrue(result.success, "Didn't replenish the limit");
    }

}
