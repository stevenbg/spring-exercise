package com.example.myrest.ingredient;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException() {
        super("Ingredient not found");
    }

    public IngredientNotFoundException(Long id) {
        super("Ingredient " + id + " not found");
    }
}
