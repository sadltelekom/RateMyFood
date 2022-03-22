package com.zeegermans.RateMyFood.controller;


import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.IngredientsDB;

import com.zeegermans.RateMyFood.model.Ingredients;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class IngredientsController {

    private Connection connection = DBConnector.getInstance().getConnection();
    IngredientsDB ingredientsDB = new IngredientsDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/ingredients/")
    public List<Ingredients> allIngredients() {
        return ingredientsDB.getAllIngredients();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/ingredients/{id}")
    public List<Ingredients> ingredientsById(@PathVariable long id) {
        return ingredientsDB.getIngredientById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/ingredients/recipe/{id}")
    public List<Ingredients> ingredientsByRecipeId(@PathVariable long id) {
        return ingredientsDB.getIngredientByRecipeId(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("post/ingredients/")
    public List<Ingredients> insertIngredients(@PathVariable String name) {
        return ingredientsDB.insertIngredients(name);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("update/ingredients/")
    public List<Ingredients> updateIngredients(@RequestBody Map<String, Object> body) {
        long id = (Long) body.get("id");
        String name = (String) body.get("name");
        return ingredientsDB.updateIngredients(id, name);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/ingredients/{id}")
    public boolean deleteIngredients(@PathVariable long id) {
        return ingredientsDB.deleteIngredients(id);
    }
}
