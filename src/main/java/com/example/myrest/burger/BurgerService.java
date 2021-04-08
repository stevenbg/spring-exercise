package com.example.myrest.burger;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository repository;
    private final BurgerMapper burgerMapper;

    public BurgerService(BurgerRepository repository, BurgerMapper burgerMapper) {
        this.burgerMapper = burgerMapper;
        this.repository = repository;
    }

    public List<BurgerDto> find(BurgerSearchParams params) {
        List<BurgerDto> out = new ArrayList<>();
        repository.findByParams(params).forEach(
                x -> { out.add(burgerMapper.toDto(x)); }
        );
        return out;
    }

    public BurgerDto add(BurgerDto b) {
        b.setId(null);
        return save(b);
    }

    public BurgerDto save(BurgerDto b) {
        Burger saved = repository.save(burgerMapper.toBurger(b));
        return burgerMapper.toDto(saved);
    }

    /**
     * Fetch a random burger
     */
    public BurgerDto fetchOne() {
        Burger burger = repository.findRandom()
                .orElseThrow(BurgerNotFoundException::new);
        return burgerMapper.toDto(burger);
    }

    /**
     * Fetch burger by id
     */
    public BurgerDto fetchOne(Long id) {
        Burger burger = repository.findById(id)
                .orElseThrow(() -> new BurgerNotFoundException(id));
        return burgerMapper.toDto(burger);
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
