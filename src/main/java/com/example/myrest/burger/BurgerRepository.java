package com.example.myrest.burger;

import com.example.myrest.system.CustomizedJpaRepository;

import java.util.List;

public interface BurgerRepository extends CustomizedJpaRepository<Burger, Long> {
    List<Burger> findByNameContainingIgnoreCase(String name);
}

