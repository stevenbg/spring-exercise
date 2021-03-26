package com.example.myrest.burger;

import com.example.myrest.RandomableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BurgerRepository extends JpaRepository<Burger, Long>, RandomableRepository<Burger> {
}
