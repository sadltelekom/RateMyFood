package com.zeegermans.RateMyFood.model;

public class Ingredients {
    private long id;
    private String name;

    public Ingredients(String name) {
        this.name = name;
    }

    public Ingredients(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
