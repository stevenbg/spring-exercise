package com.example.myrest.burger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class BurgerRepositoryExtImpl implements BurgerRepositoryExt {

    @Lazy
    @Autowired
    private BurgerRepository repository;

    @Override
    public List<Burger> findByParams(BurgerSearchParams params) {
        BurgerSearchSpecification spec = new BurgerSearchSpecification(params);

        Page<Burger> result = repository.findAll(spec,
                PageRequest.of(params.getPage() - 1, params.getPer_page()));

        return result.getContent();
    }

}
