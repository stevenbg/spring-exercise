package com.example.myrest.ingredient;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository repository;
    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository repository, IngredientMapper ingredientMapper) {
        this.repository = repository;
        this.ingredientMapper = ingredientMapper;
    }

    public List<IngredientDto> findByName(String name) {
        List<IngredientDto> out = new ArrayList<>();
        repository.findByNameContainingIgnoreCase(name).forEach(
                x -> { out.add(ingredientMapper.toDto(x)); }
        );
        return out;
    }

    public IngredientDto add(IngredientDto i) {
        i.setId(null);
        Ingredient saved = repository.save(ingredientMapper.toIngredient(i));
        return ingredientMapper.toDto(saved);
    }

    /**
     * Fetch ingredient by id
     */
    public IngredientDto fetchOne(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));

        return ingredientMapper.toDto(ingredient);
    }

    public List<IngredientDto> fetchMany(List<Long> ids) {
        List<IngredientDto> out = new ArrayList<>();

        repository.findByIdIn(ids).forEach(
                x -> {
                    ids.remove(x.getId());
                    out.add(ingredientMapper.toDto(x));
                }
        );

        if (ids.size() > 0) {
            throw new IngredientNotFoundException(ids);
        }

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
