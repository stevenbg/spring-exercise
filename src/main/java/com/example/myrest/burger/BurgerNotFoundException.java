package com.example.myrest.burger;

public class BurgerNotFoundException extends RuntimeException {
    public BurgerNotFoundException() {
        super();
    }
    public BurgerNotFoundException(String message) {
        super(message);
    }
}
