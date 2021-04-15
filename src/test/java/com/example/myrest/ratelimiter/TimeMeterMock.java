package com.example.myrest.ratelimiter;

import io.github.bucket4j.TimeMeter;

public class TimeMeterMock implements TimeMeter {
    private long currentTimeNanos;

    public TimeMeterMock() {
        currentTimeNanos = 0;
    }

    public void addSeconds(long seconds) {
        currentTimeNanos += seconds * 1_000_000_000;
    }

    @Override
    public long currentTimeNanos() {
        return currentTimeNanos;
    }

    @Override
    public boolean isWallClockBased() {
        return true;
    }
}
