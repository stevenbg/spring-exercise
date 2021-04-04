package com.example.myrest.ingredient;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException() {
        super("Ingredient not found");
    }

    public IngredientNotFoundException(Long id) {
        super("Ingredient " + id + " not found");
    }

    public IngredientNotFoundException(List<Long> ids) {
        super("Ingredient not found: " + ids.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
