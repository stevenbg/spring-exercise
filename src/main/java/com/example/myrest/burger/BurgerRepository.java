package com.example.myrest.burger;

import com.example.myrest.system.CustomizedJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BurgerRepository extends CustomizedJpaRepository<Burger, Long>,
        BurgerRepositoryExt, JpaSpecificationExecutor<Burger> {

    List<Burger> findByNameContainingIgnoreCase(String name);
}

