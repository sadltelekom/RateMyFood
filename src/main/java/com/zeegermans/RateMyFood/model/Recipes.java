package com.zeegermans.RateMyFood.model;

public class Recipes {
    private long id;
    private String name;
    private int time;
    private String howto;

    public Recipes(String name, int time, String howto) {
        this.name = name;
        this.time = time;
        this.howto = howto;
    }

    public Recipes(long id, String name, int time, String howto) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.howto = howto;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getHowto() {
        return howto;
    }

    public void setHowto(String howto) {
        this.howto = howto;
    }
}
