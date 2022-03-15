package com.zeegermans.RateMyFood.model;

import com.zeegermans.RateMyFood.enums.CategoryList;
import com.zeegermans.RateMyFood.enums.CourseList;

public class Category {
    private long id;
    private CategoryList category;
    private CourseList course;


    public Category(CategoryList category, CourseList course) {
        this.category = category;
        this.course = course;
    }

    public Category(long id, CategoryList category, CourseList course) {
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

    public CategoryList getCategory() {
        return category;
    }

    public void setCategory(CategoryList category) {
        this.category = category;
    }

    public CourseList getCourse() {
        return course;
    }

    public void setCourse(CourseList course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category=" + category +
                ", course=" + course +
                '}';
    }
}
