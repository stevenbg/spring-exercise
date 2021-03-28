package com.example.myrest;

import com.example.myrest.burger.BurgerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Minimal errors - status code and log.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BurgerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void burgerNotFoundHandler(BurgerNotFoundException ex) {
        logger.warn(ex.getMessage());
    }

    @ExceptionHandler(RateLimiterException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    void tooManyRequestsHandler(RateLimiterException ex) {
        logger.warn(ex.getMessage());
    }

}
