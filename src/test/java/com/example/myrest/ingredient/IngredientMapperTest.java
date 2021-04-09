package com.example.myrest.ingredient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientMapperTest {
    private IngredientMapper ingredientMapper;

    @BeforeEach
    void setUp() {
        ingredientMapper = new IngredientMapper();
    }

//    todo object equals
    @Test
    void convertDtoToIngredient() {
        Ingredient expected = new Ingredient(2L, "testing");
        IngredientDto ingredientDto = new IngredientDto(2L, "testing");
        Ingredient actual = ingredientMapper.toIngredient(ingredientDto);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void convertIngredientToDto() {
        IngredientDto expected = new IngredientDto(2L, "testing");
        Ingredient ingredient = new Ingredient(2L, "testing");
        IngredientDto actual = ingredientMapper.toDto(ingredient);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
