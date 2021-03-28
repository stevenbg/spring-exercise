package com.example.myrest.burger;

import com.example.myrest.model.BaseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Burger extends BaseEntity {
    @NotBlank
    private String name;

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
}
