package com.zeegermans.RateMyFood.model;

public class Rating {
    private long id;
    private int rating;

    public Rating(int rating) {
        this.rating = rating;
    }

    public Rating(long id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
