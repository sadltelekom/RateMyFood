package com.zeegermans.RateMyFood.model;

import com.zeegermans.RateMyFood.enums.categoryList;
import com.zeegermans.RateMyFood.enums.courseList;

public class Category {
    private long id;
    private categoryList category;
    private courseList course;


    public Category(categoryList category, courseList course) {
        this.category = category;
        this.course = course;
    }

    public Category(long id, categoryList category, courseList course) {
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

    public categoryList getCategory() {
        return category;
    }

    public void setCategory(categoryList category) {
        this.category = category;
    }

    public courseList getCourse() {
        return course;
    }

    public void setCourse(courseList course) {
        this.course = course;
    }
}
