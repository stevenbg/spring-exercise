/**
 * Leaks nothing. Only logs and sets a status code.
 */
package com.example.myrest;

import com.example.myrest.burger.BurgerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;



// todo spring's exceptions

@ControllerAdvice
// extends ResponseEntityExceptionHandler
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BurgerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void burgerNotFoundHandler(BurgerNotFoundException ex) {
        logger.error(ex.getMessage());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
//        return error(exception.getBindingResult().getFieldErrors()
//                .stream()
//                .map(FieldError::getDefaultMessage)
//                .collect(Collectors.toList()));
//    }
//
//    private Map error(Object message) {
//        return Collections.singletonMap("error", message);
//    }

//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map handle(ConstraintViolationException exception) {
//        return error(exception.getConstraintViolations()
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList()));
//    }


}
