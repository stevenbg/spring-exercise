package com.example.myrest.ratelimiter;

public class RateLimiterException extends RuntimeException {

    public RateLimiterException() {
        super();
    }

    public RateLimiterException(String message) {
        super(message);
    }

}
