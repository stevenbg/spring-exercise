package com.example.myrest;

public class RateLimiterException extends RuntimeException {

    public RateLimiterException() {
        super();
    }

    public RateLimiterException(String message) {
        super(message);
    }

}
