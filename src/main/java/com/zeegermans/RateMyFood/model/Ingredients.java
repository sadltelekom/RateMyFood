package com.zeegermans.RateMyFood.model;

import com.zeegermans.RateMyFood.enums.ingredientsList;

public class Ingredients {
    private long id;
    private ingredientsList name;

    public Ingredients(ingredientsList name) {
        this.name = name;
    }

    public Ingredients(long id, ingredientsList name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ingredientsList getName() {
        return name;
    }

    public void setName(ingredientsList name) {
        this.name = name;
    }
}
