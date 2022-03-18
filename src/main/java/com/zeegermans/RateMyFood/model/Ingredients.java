package com.zeegermans.RateMyFood.model;


public class Ingredients {
    private long id;
    private String name;
    private String amount;

    public Ingredients(String name) {
        this.name = name;
    }

    public Ingredients(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Ingredients(long id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
