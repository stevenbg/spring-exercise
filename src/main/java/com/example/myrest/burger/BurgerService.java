package com.example.myrest.burger;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository repository;

    public BurgerService(BurgerRepository repository) {
        this.repository = repository;
    }

    private Burger burgerFromParams(BurgerServiceFindParams params) {
        Burger b = new Burger();
        if (params.getName() != null) {
            b.setName(params.getName());
        }

        return b;
    }

    public List<Burger> find(BurgerServiceFindParams params) {
        Burger burgerToMatch = burgerFromParams(params);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Burger> example = Example.of(burgerToMatch, exampleMatcher);

        Page<Burger> result = repository.findAll(example,
                PageRequest.of(params.getPage() - 1, params.getPer_page()));

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
