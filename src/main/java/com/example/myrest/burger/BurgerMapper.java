package com.example.myrest.burger;

import com.example.myrest.ingredient.IngredientDto;
import com.example.myrest.ingredient.IngredientMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BurgerMapper {
    private IngredientMapper ingredientMapper;

    public BurgerMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    Burger toBurger(BurgerDto burgerDto) {
        Burger out = new Burger();
        out.setId(burgerDto.getId());
        out.setName(burgerDto.getName());

        burgerDto.getIngredients().forEach(x -> {
                out.addIngredient(ingredientMapper.toIngredient(x));
            });

        return out;
    }

    BurgerDto toDto(Burger burger) {
        BurgerDto out = new BurgerDto();
        out.setId(burger.getId());
        out.setName(burger.getName());


        List<IngredientDto> ingredientsDto = burger.getIngredients().stream().map(x -> {
            return ingredientMapper.toDto(x);
        }).collect(Collectors.toList());

        out.setIngredients(ingredientsDto);

        return out;
    }
}
