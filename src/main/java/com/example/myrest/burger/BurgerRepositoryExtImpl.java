package com.example.myrest.burger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class BurgerRepositoryExtImpl implements BurgerRepositoryExt {

    @Lazy
    @Autowired
    private BurgerRepository repository;

    @Override
    public List<Burger> findByParams(BurgerSearchParams params) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Burger> example = Example.of(params.burgerize(), exampleMatcher);

        Page<Burger> result = repository.findAll(example,
                PageRequest.of(params.getPage() - 1, params.getPer_page()));

//        or loop over params with a query builder

        return result.getContent();
    }
}
