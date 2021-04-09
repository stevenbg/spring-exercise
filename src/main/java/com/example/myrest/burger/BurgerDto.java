package com.example.myrest.burger;

import com.example.myrest.ingredient.IngredientDto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class BurgerDto {
    private Long id;

    @NotBlank
    private String name;

    private final List<IngredientDto> ingredients = new ArrayList<>();

    public BurgerDto() {
    }
    public BurgerDto(Long id, String name, List<IngredientDto> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients.addAll(ingredients);
    }

    public List<IngredientDto> getIngredients() {
        return new ArrayList<IngredientDto>(ingredients);
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients.clear();
        this.ingredients.addAll(ingredients);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
