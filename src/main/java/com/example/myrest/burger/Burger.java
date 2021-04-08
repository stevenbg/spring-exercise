package com.example.myrest.burger;

import com.example.myrest.ingredient.Ingredient;
import com.example.myrest.model.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Burger extends BaseEntity {
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "burger_ingredient",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    public Burger() {}
    public Burger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.addBurger(this);
    }
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.removeBurger(this);
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients);
    }
}
