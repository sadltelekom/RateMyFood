package com.zeegermans.RateMyFood.model;

public class Category {
    private long id;
    private String category;
    private String course;


    public Category(String category, String course) {
        this.category = category;
        this.course = course;
    }

    public Category(long id, String category, String course) {
        this.id = id;
        this.category = category;
        this.course = course;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
