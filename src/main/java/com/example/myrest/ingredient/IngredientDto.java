package com.example.myrest.ingredient;

import javax.validation.constraints.NotBlank;

public class IngredientDto {
    private Long id;

    @NotBlank
    private String name;

    public IngredientDto() {
    }

    public IngredientDto(Ingredient ingredient) {
        id = ingredient.getId();
        name = ingredient.getName();
    }

    public Ingredient toIngredient() {
        Ingredient b = new Ingredient(getName());
        b.setId(getId());
        return b;
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
