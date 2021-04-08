package com.example.myrest.ingredient;

import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public Ingredient toIngredient(IngredientDto ingredientDto) {
        Ingredient i = new Ingredient(ingredientDto.getName());
        i.setId(ingredientDto.getId());
        return i;
    }

    public IngredientDto toDto(Ingredient ingredient) {
        IngredientDto out = new IngredientDto();
        out.setId(ingredient.getId());
        out.setName(ingredient.getName());
        return out;
    }

}
