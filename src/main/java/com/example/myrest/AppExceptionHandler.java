package com.example.myrest;

import com.example.myrest.burger.BurgerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BurgerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String burgerNotFoundHandler(BurgerNotFoundException ex) {
//        minimum traffic until further requirements
        return "";
    }

}
