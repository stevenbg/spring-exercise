package com.example.myrest.burger;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository repository;

    public BurgerService(BurgerRepository repository) {
        this.repository = repository;
    }

    public List<Burger> find(BurgerSearchParams params) {
        List<Burger> result = repository.findByParams(params);
//        do stuff
        return result;
    }

    public Burger add(Burger b) {
        b.setId(null);
        b = repository.save(b);
        return b;
    }

    /**
     * Fetch a random burger
     */
    public Burger fetchOne() {
        return repository.findRandom()
                .orElseThrow(BurgerNotFoundException::new);
    }

    /**
     * Fetch burger by id
     */
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
