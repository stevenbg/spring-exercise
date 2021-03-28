package com.example.myrest.burger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class BurgerListParams {
    @Min(1)
    @Max(100000)
    public Integer page = 1;
    @Min(1)
    @Max(1000)
    public Integer per_page = 25;

    @Size(min = 3, max = 255)
    public String name;

    public Integer getPage() {
        return page;
    }

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
