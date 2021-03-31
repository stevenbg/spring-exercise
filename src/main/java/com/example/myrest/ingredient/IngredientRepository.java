package com.example.myrest.ingredient;

import com.example.myrest.system.CustomizedJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CustomizedJpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameContainingIgnoreCase(String name);
    List<Ingredient> findByIdIn(List<Long> ids);
}

