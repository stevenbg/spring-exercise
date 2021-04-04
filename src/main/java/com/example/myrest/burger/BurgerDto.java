package com.example.myrest.burger;

import com.example.myrest.ingredient.IngredientDto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BurgerDto {
    private Long id;

    @NotBlank
    private String name;

    private List<IngredientDto> ingredients = new ArrayList<>();

    public BurgerDto() {
    }

    public BurgerDto(Burger burger) {
        this.id = burger.getId();
        this.name = burger.getName();
        ingredients = burger.getIngredients()
                .stream().map(IngredientDto::new)
                .collect(Collectors.toList());
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public Burger toBurger() {
        Burger b = new Burger(getName());
        b.setId(getId());
        getIngredients().forEach(x -> {
            b.addIngredient(x.toIngredient());
        });
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
