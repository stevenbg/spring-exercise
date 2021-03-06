package com.example.myrest.ingredient;

import javax.validation.constraints.NotBlank;

public class IngredientDto {
    private Long id;

    @NotBlank
    private String name;

    public IngredientDto() {
    }
    public IngredientDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
