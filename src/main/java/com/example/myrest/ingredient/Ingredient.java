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
        this(null, name);
    }

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBurger(Burger burger) {
        this.burgers.add(burger);
    }
    public void removeBurger(Burger burger) {
        this.burgers.remove(burger);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
