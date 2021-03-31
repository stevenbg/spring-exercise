package com.example.myrest.burger;

import com.example.myrest.ingredient.Ingredient;
import com.example.myrest.ingredient.IngredientDto;
import com.example.myrest.ingredient.IngredientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository repository;

    public BurgerService(BurgerRepository repository) {
        this.repository = repository;
    }

    public List<BurgerDto> find(BurgerSearchParams params) {
        List<BurgerDto> out = new ArrayList<>();
        repository.findByParams(params).forEach(
                x -> { out.add(new BurgerDto(x)); }
        );
        return out;
    }

    public BurgerDto add(BurgerDto b) {
        b.setId(null);
        return new BurgerDto(repository.save(b.toBurger()));
    }

    public BurgerDto save(BurgerDto b) {
        b = new BurgerDto(repository.save(b.toBurger()));
        return b;
    }

    /**
     * Fetch a random burger
     */
    public BurgerDto fetchOne() {
        Burger burger = repository.findRandom()
                .orElseThrow(() -> new BurgerNotFoundException());
        return new BurgerDto(burger);
    }

    /**
     * Fetch burger by id
     */
    public BurgerDto fetchOne(Long id) {
        Burger burger = repository.findById(id)
                .orElseThrow(() -> new BurgerNotFoundException(id));
        return new BurgerDto(burger);
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
