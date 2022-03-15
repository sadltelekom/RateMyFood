package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.CategoryDB;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    CategoryDB category = new CategoryDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/categories/")
    public Object allCategories() {
        return category.getAllCategories();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/courses/")
    public Object allCourses() {
        return category.getAllCourses();
    }
}
