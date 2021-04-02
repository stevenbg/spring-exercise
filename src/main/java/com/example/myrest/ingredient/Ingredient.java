package com.example.myrest.ingredient;

import com.example.myrest.burger.Burger;
import com.example.myrest.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Burger> burgers = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Set<Burger> getBurgers() {
        return burgers;
    }

    public void setBurgers(Set<Burger> burgers) {
        this.burgers = burgers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
