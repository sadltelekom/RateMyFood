package com.zeegermans.RateMyFood.model;

public class Comments {
    private long id;
    private String comment;

    public Comments(String comment) {
        this.comment = comment;
    }

    public Comments(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
