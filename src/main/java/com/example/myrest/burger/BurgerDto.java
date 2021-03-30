package com.example.myrest.burger;

import javax.validation.constraints.NotBlank;

public class BurgerDto {
    private Long id;

    @NotBlank
    private String name;

    public BurgerDto() {
    }

    public BurgerDto(Burger burger) {
        id = burger.getId();
        name = burger.getName();
    }

    public Burger toBurger() {
        Burger b = new Burger(getName());
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
