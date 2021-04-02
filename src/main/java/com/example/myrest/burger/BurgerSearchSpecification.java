package com.example.myrest.burger;

import com.example.myrest.ingredient.Ingredient;
import com.example.myrest.ingredient.Ingredient_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

public class BurgerSearchSpecification implements Specification<Burger> {

    private final BurgerSearchParams params;

    public BurgerSearchSpecification(BurgerSearchParams params) {
        this.params = params;
    }

    @Override
    public Predicate toPredicate(Root<Burger> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (isEmpty(params.getName()) == false) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get(com.example.myrest.burger.Burger_.name)),
                    "%" + String.valueOf(params.getName()).toUpperCase() + "%")));
        }

        if (isEmpty(params.getIngredients()) == false) {
            Join<Burger, Ingredient> root_ingredients = root.join(com.example.myrest.burger.Burger_.ingredients, JoinType.INNER);
            predicates.add(criteriaBuilder.and(root_ingredients.get(Ingredient_.id).in(params.getIngredients())));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

}
