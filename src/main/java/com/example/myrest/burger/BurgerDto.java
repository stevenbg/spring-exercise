package com.example.myrest.burger;

import com.example.myrest.ingredient.IngredientDto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class BurgerDto {
    private Long id;

    @NotBlank
    private String name;

    private List<IngredientDto> ingredients = new ArrayList<>();

    public BurgerDto() {
    }

    public List<IngredientDto> getIngredients() {
        return List.copyOf(ingredients);
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = List.copyOf(ingredients);
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
