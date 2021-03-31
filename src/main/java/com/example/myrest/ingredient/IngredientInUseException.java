package com.example.myrest.ingredient;

public class IngredientInUseException extends RuntimeException {
    public IngredientInUseException(Long id) {
        super("Ingredient " + id + " is still in use");
    }
}
