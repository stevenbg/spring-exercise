package com.example.myrest.system;

import com.example.myrest.burger.BurgerNotFoundException;
import com.example.myrest.ingredient.IngredientInUseException;
import com.example.myrest.ingredient.IngredientNotFoundException;
import com.example.myrest.ratelimiter.RateLimiterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Minimal errors - status code and log.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BurgerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void burgerNotFoundHandler(BurgerNotFoundException ex) {
        logger.warn(ex.getMessage());
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void ingredientNotFoundHandler(IngredientNotFoundException ex) {
        logger.warn(ex.getMessage());
    }

    @ExceptionHandler(IngredientInUseException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    void ingredientNotFoundHandler(IngredientInUseException ex) {
        logger.warn(ex.getMessage());
    }

    @ExceptionHandler(RateLimiterException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    void tooManyRequestsHandler(RateLimiterException ex) {
        logger.warn(ex.getMessage());
    }

//    500
}
