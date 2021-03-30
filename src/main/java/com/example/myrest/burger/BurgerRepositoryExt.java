package com.example.myrest.burger;

import java.util.List;

public interface BurgerRepositoryExt {
    List<Burger> findByParams(BurgerSearchParams params);
}
