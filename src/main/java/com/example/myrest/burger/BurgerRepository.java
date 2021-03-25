package com.example.myrest.burger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BurgerRepository extends JpaRepository<Burger, Long>, MyBurgerRepository {
}

// Is this bundling acceptable, since it's all tied to BurgerRepository?
interface MyBurgerRepository {
    Optional<Burger> findRandom();
}

class MyBurgerRepositoryImpl implements MyBurgerRepository {

    @Autowired
    @Lazy
    BurgerRepository repository;

    public Optional<Burger> findRandom() {
//        SOL over 4bn
        int index = (int) Math.round((Math.random() * repository.count()));
        Page<Burger> result = repository.findAll(PageRequest.of(index, 1));
        Optional<Burger> b = Optional.empty();

        if (result.hasContent()) {
            b = Optional.ofNullable(result.getContent().get(0));
        }

        return b;
    }
}