package com.example.myrest.ingredient;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<IngredientDto> findByName(String name) {
        List<IngredientDto> out = new ArrayList<>();
        repository.findByNameContainingIgnoreCase(name).forEach(
                x -> { out.add(new IngredientDto(x)); }
        );
        return out;
    }

    public IngredientDto add(IngredientDto i) {
        i.setId(null);
        return new IngredientDto(repository.save(i.toIngredient()));
    }

    /**
     * Fetch ingredient by id
     */
    public IngredientDto fetchOne(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
        return new IngredientDto(ingredient);
    }

    public List<IngredientDto> fetch(List<Long> ids) {
        List<IngredientDto> out = new ArrayList<>();
        repository.findByIdIn(ids).forEach(
                x -> { out.add(new IngredientDto(x)); }
        );
        return out;
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            try {
                repository.deleteById(id);
            }
            catch (DataIntegrityViolationException e) {
                throw new IngredientInUseException(id);
            }
        }
        else {
            throw new IngredientNotFoundException(id);
        }
    }
}
