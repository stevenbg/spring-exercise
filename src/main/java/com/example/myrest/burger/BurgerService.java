package com.example.myrest.burger;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BurgerService {
    private final BurgerRepository repository;

    public BurgerService(BurgerRepository repository) {
        this.repository = repository;
    }

    public List<Burger> find(BurgerListParams params) {
        Burger b = new Burger();
        if (params.getName() != null) {
            b.setName(params.getName());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Burger> example = Example.of(b, exampleMatcher);
        Page<Burger> result = repository.findAll(example, PageRequest.of(params.page - 1, params.per_page));

        return result.getContent();
    }

    public Burger add(Burger b) {
        b.setId(null);
        b = repository.save(b);
        return b;
    }

    public Burger fetchOne() {
        return repository.findRandom()
                .orElseThrow(BurgerNotFoundException::new);
    }

    public Burger fetchOne(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BurgerNotFoundException(id));
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new BurgerNotFoundException(id);
        }
    }
}
