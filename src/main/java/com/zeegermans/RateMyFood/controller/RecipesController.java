package com.zeegermans.RateMyFood.controller;


import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.RecipesDB;
import com.zeegermans.RateMyFood.model.Recipes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RecipesController {

    Connection connection = DBConnector.getInstance().getConnection();
    RecipesDB recipesDB = new RecipesDB();

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/all/")
    public List<Recipes> recipes() {
        return recipesDB.getAllRecipes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/id/{id}")
    public List<Recipes> recipesById(@PathVariable long id) {
        System.out.println("got not far");
        return recipesDB.getRecipesById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/userid/{id}")
    public List<Recipes> recipesByUserId(@PathVariable long id) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactUserId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/categoryid/{id}")
    public List<Recipes> recipesByExactCategoryId(@PathVariable long id) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactCategoryId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/ingredientsid/{id}")
    public List<Recipes> recipesByExactIngredientsId(@PathVariable long id) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactIngredientsId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/category/{category}")
    public List<Recipes> recipesByExactCategory(@PathVariable String category) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactCategory(category);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/ingredient/{ingredient}")
    public List<Recipes> recipesByExactIngredient(@PathVariable String ingredient) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactIngredientsName(ingredient);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/name/{name}")
    public List<Recipes> recipesByExactName(@PathVariable String name) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/username/{username}")
    public List<Recipes> recipesByExactUserName(@PathVariable String username) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactUserName(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/categorypart/{category}")
    public List<Recipes> recipesByPartOfCategory(@PathVariable String category) {
        System.out.println("got not far");
        return recipesDB.getRecipesByExactCategory(category);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/ingredientpart/{ingredient}")
    public List<Recipes> recipesByPartOfIngredient(@PathVariable String ingredient) {
        System.out.println("got not far");
        return recipesDB.getRecipesByPartOfIngredientsName(ingredient);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/namepart/{name}")
    public List<Recipes> recipesByPartOfName(@PathVariable String name) {
        System.out.println("got not far");
        return recipesDB.getRecipesByPartOfName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/usernamepart/{username}")
    public List<Recipes> recipesByPartOfUserName(@PathVariable String username) {
        System.out.println("got not far");
        return recipesDB.getRecipesByPartOfUserName(username);
    }




}
