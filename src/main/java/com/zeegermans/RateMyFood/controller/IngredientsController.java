package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.CommentsDB;
import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.IngredientsDB;
import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Ingredients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

@RestController
public class IngredientsController {
    // Todo: Get all ingredients by recipe ID

    private Connection connection = DBConnector.getInstance().getConnection();
    IngredientsDB ingredients = new IngredientsDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/ingredients/")
    public List<Ingredients> allIngredients() {
        return ingredients.getAllIngredients();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/ingredients/{id}")
    public List<Ingredients> ingredientsById(@PathVariable long id) {
        return ingredients.getIngredientById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/ingredients/recipe/{id}")
    public List<Ingredients> ingredientsByRecipeId(@PathVariable long id) {
        return ingredients.getIngredientByRecipeId(id);
    }
}
