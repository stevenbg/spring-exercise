package com.example.myrest.burger;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Burger {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    public Burger() {}
    public Burger(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return name;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}
