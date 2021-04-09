package com.example.myrest.burger;

import com.example.myrest.ingredient.Ingredient;
import com.example.myrest.ingredient.IngredientDto;
import com.example.myrest.ingredient.IngredientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class BurgerMapperTest {
    private BurgerMapper burgerMapper;

    @BeforeEach
    void setUp() {
        IngredientMapper mockMapper = Mockito.mock(IngredientMapper.class);
        Mockito.when(mockMapper.toDto(any(Ingredient.class)))
                .thenReturn(new IngredientDto(1L, "test ingredient"));
        Mockito.when(mockMapper.toIngredient(any(IngredientDto.class)))
                .thenReturn(new Ingredient(1L, "test ingredient"));

        burgerMapper = new BurgerMapper(mockMapper);
    }

//    todo object equals
    @Test
    void convertDtoToBurger() {
        Burger expected = new Burger(1L, "test burger", List.of(new Ingredient(1L, "test ingredient")));

        BurgerDto burgerDto = new BurgerDto(1L, "test burger", List.of(new IngredientDto(1L, "test ingredient")));
        Burger actual = burgerMapper.toBurger(burgerDto);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());

        Ingredient expectedIngredient = expected.getIngredients().get(0);
        Ingredient actualIngredient = actual.getIngredients().get(0);
        assertEquals(expectedIngredient.getId(), actualIngredient.getId());
        assertEquals(expectedIngredient.getName(), actualIngredient.getName());
    }

    @Test
    void convertBurgerToDto() {
        BurgerDto expected = new BurgerDto(1L, "test burger", List.of(new IngredientDto(1L, "test ingredient")));

        Burger burger = new Burger(1L, "test burger", List.of(new Ingredient(1L, "test ingredient")));
        BurgerDto actual = burgerMapper.toDto(burger);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());

        IngredientDto expectedIngredientDto = expected.getIngredients().get(0);
        IngredientDto actualIngredientDto = actual.getIngredients().get(0);
        assertEquals(expectedIngredientDto.getId(), actualIngredientDto.getId());
        assertEquals(expectedIngredientDto.getName(), actualIngredientDto.getName());
    }
}
