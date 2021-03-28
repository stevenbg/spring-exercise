package com.example.myrest.burger;

public class BurgerNotFoundException extends RuntimeException {
    public BurgerNotFoundException() {
        super("Burger not found");
    }

    public BurgerNotFoundException(Long id) {
        super("Burger " + id + " not found");
    }
}
