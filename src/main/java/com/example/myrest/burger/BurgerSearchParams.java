package com.example.myrest.burger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the possible search parameters
 */
public class BurgerSearchParams {
    @Min(1)
    @Max(100000)
    private Integer page = 1;

    @Min(1)
    @Max(1000)
    private Integer per_page = 25;

    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 3)
    private List<Long> ingredients = new ArrayList<>();

    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * The page index starts at 1
     */
    public Integer getPage() {
        return page;
    }

    /**
     * The page index starts at 1
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {

        this.per_page = per_page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
