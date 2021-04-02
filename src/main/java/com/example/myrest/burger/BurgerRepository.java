package com.example.myrest.burger;

import com.example.myrest.system.CustomizedJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends CustomizedJpaRepository<Burger, Long>,
        BurgerRepositoryExt, JpaSpecificationExecutor<Burger> {

}

