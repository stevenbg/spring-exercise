package com.example.myrest.ratelimiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.cache.Caching;

import static org.junit.jupiter.api.Assertions.*;

class RateLimiterIntTest {
    private final Integer rate = 2;
    private final Integer interval = 10;
    private final String key = "key";
    private RateLimiter rateLimiter;
    private TimeMeterMock timeMeterMock;

    @BeforeEach
    void setUp() {
        timeMeterMock = new TimeMeterMock();
        rateLimiter = new RateLimiter(rate, interval, Caching.getCachingProvider().getCacheManager(), timeMeterMock);
    }

    private void repeatAction(Integer times) {
        for (int i = 0; i < times; i++) {
            rateLimiter.attemptAction(key);
        }
    }

    @Test
    void attemptActionAndGetProperRemaining() {
        RateLimiter.Result result = rateLimiter.attemptAction(key);
        Integer expected = rate - 1;
        if (expected < 0) {
            expected = 0;
        }
        assertEquals(expected.longValue(), result.remaining, "Wrong remaining count");
    }

    @Test
    void attemptActionAboveLimitAndGetProperWait() {
        repeatAction(rate);

        RateLimiter.Result result = rateLimiter.attemptAction(key);

        assertEquals(interval.longValue(), result.wait, "Wrong wait time (before tick)");
        assertFalse(result.success, "Allowed an action past limits");

        Long expected = interval.longValue() - 1;
        if (expected > 0) {
            timeMeterMock.addSeconds(1);
            result = rateLimiter.attemptAction(key);

            assertEquals(expected, result.wait, "Wrong wait time (after tick)");
            assertFalse(result.success, "Allowed an action past limits");
        }
    }

    @Test
    void attemptActionUpToRateLimitAndSucceed() {
        repeatAction(rate - 1);

        RateLimiter.Result result = rateLimiter.attemptAction(key);

        assertEquals(0, result.remaining);
        assertTrue(result.success, "Denied an action within limits");
    }

    @Test
    void attemptActionAboveRateLimitAndFail() {
        repeatAction(rate);

        RateLimiter.Result result = rateLimiter.attemptAction(key);

        assertEquals(0, result.remaining);
        assertFalse(result.success, "Allowed an action past limits");
    }

    @Test
    void exhaustAttemptsThenReplenishAndSucceed() {
        repeatAction(rate);
        timeMeterMock.addSeconds(interval);
        RateLimiter.Result result = rateLimiter.attemptAction(key);

        assertEquals(Long.valueOf(rate - 1), result.remaining);
        assertTrue(result.success, "Didn't replenish the limit");
    }

}
