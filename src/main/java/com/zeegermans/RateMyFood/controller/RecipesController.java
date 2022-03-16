package com.zeegermans.RateMyFood.controller;


import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.RecipesDB;
import com.zeegermans.RateMyFood.model.Recipes;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class RecipesController {
    // Todo: get Course by Recipe ID
    // TODO: get Category bay Recipe ID



    Connection connection = DBConnector.getInstance().getConnection();
    RecipesDB recipesDB = new RecipesDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/all/")
    public List<Recipes> recipes() {
        return recipesDB.getAllRecipes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/id/{id}")
    public List<Recipes> recipesById(@PathVariable long id) {
        return recipesDB.getRecipesById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/userid/{id}")
    public List<Recipes> recipesByUserId(@PathVariable long id) {
        return recipesDB.getRecipesByExactUserId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/categoryid/{id}")
    public List<Recipes> recipesByExactCategoryId(@PathVariable long id) {
        return recipesDB.getRecipesByExactCategoryId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/ingredientsid/{id}")
    public List<Recipes> recipesByExactIngredientsId(@PathVariable long id) {
        return recipesDB.getRecipesByExactIngredientsId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/category/{category}")
    public List<Recipes> recipesByExactCategory(@PathVariable String category) {
        return recipesDB.getRecipesByExactCategory(category);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/ingredient/{ingredient}")
    public List<Recipes> recipesByExactIngredient(@PathVariable String ingredient) {
        return recipesDB.getRecipesByExactIngredientsName(ingredient);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/name/{name}")
    public List<Recipes> recipesByExactName(@PathVariable String name) {
        return recipesDB.getRecipesByExactName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/username/{username}")
    public List<Recipes> recipesByExactUserName(@PathVariable String username) {
        return recipesDB.getRecipesByExactUserName(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/categorypart/{category}")
    public List<Recipes> recipesByPartOfCategory(@PathVariable String category) {
        return recipesDB.getRecipesByPartOfCategory(category);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/ingredientpart/{ingredient}")
    public List<Recipes> recipesByPartOfIngredient(@PathVariable String ingredient) {
        return recipesDB.getRecipesByPartOfIngredientsName(ingredient);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/namepart/{name}")
    public List<Recipes> recipesByPartOfName(@PathVariable String name) {
        return recipesDB.getRecipesByPartOfName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/usernamepart/{username}")
    public List<Recipes> recipesByPartOfUserName(@PathVariable String username) {
        return recipesDB.getRecipesByPartOfUserName(username);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("post/recipes/")
    public List<Recipes> newRecipes(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        int time = (Integer) body.get("time");
        String howto = (String) body.get("howto");
        return recipesDB.insertRecipes(name, time, howto);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("update/recipes/")
    public List<Recipes> updateRecipes(@RequestBody Map<String, Object> body) {
        long id = (Long) body.get("id");
        String name = (String) body.get("name");
        int time = (Integer) body.get("time");
        String howto = (String) body.get("howto");

        return recipesDB.updateRecipes(id,name, time, howto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/recipes/{id}")
    public boolean deleteRecipes(@PathVariable long id) {
        return recipesDB.deleteRecipes(id);
    }


}
