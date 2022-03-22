package com.zeegermans.RateMyFood.controller;


import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.IngredientsDB;
import com.zeegermans.RateMyFood.db.RecipesDB;
import com.zeegermans.RateMyFood.enums.CategoryList;
import com.zeegermans.RateMyFood.model.Ingredients;
import com.zeegermans.RateMyFood.model.Recipes;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecipesController {

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
    @GetMapping("get/recipes/category/{id}")
    public Map recipesCategory(@PathVariable long id) {
        Map<String, String> result = new HashMap<>();
        try {
            result.put("category", recipesDB.getRecipesCategory(id).get(0));
        } catch (Exception e) {
            result.put("category", CategoryList.MEATLOVER.toString());
        }


        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/course/{id}")
    public Map recipesCourse(@PathVariable long id) {
        Map<String, String> result = new HashMap<>();
        result.put("category", recipesDB.getRecipesCourse(id).get(0));
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/ingredients/{id}")
    public Map recipesIngredients(@PathVariable long id) {
        Map<String, String> result = new HashMap<>();
        result.put("ingredients", recipesDB.getRecipesIngredients(id).get(0));
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/ingredientsid/{id}")
    public List<Recipes> recipesByExactIngredientsId(@PathVariable long id) {
        return recipesDB.getRecipesByExactIngredientsId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/categoryname/{category}")
    public List<Recipes> recipesByExactCategory(@PathVariable String category) {
        return recipesDB.getRecipesByExactCategory(category);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/search/{search}")
    public List<Recipes> recipesBySearch(@PathVariable String search) {
        return recipesDB.getRecipesBySearch(search);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/recipes/coursename/{course}")
    public List<Recipes> recipesByExactCourse(@PathVariable String course) {
        return recipesDB.getRecipesByExactCourse(course);
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
        List<Recipes> filtered = new ArrayList<>();
        IngredientsDB ingredientsDB = new IngredientsDB();

        System.out.println("Got new Recipe");

        String name = (String) body.get("name");
        int time = (Integer) body.get("time");
        String howto = (String) body.get("howto");
        String category = (String) body.get("category");
        ArrayList<HashMap<String, String>> ingredients = new ArrayList<>();
        ingredients = (ArrayList<HashMap<String, String>>) body.get("ingredients");
        List<Ingredients> newIngredientsArray = new ArrayList<>();

        Recipes newRecipe = recipesDB.insertRecipes(name, time, howto).get(0);
        long newRecipeId = newRecipe.getId();

        System.out.println("New Recipe ID = " + newRecipeId);

        for (HashMap<String, String> loopIngredient : ingredients) {
            Ingredients newIngredient = new Ingredients(
                    Long.parseLong(loopIngredient.get("id")),
                    loopIngredient.get("name"),
                    loopIngredient.get("amount")
            );
            newIngredientsArray.add(newIngredient);
        }

        for (Ingredients newOne : newIngredientsArray) {
            ingredientsDB.insertIngredientToRecipe(newOne, newRecipeId);
            System.out.println("Added :" + newOne);
        }

        filtered.add(newRecipe);

        return filtered;

    }

    @CrossOrigin(origins = "*")
    @PatchMapping("update/recipes/")
    public List<Recipes> updateRecipes(@RequestBody Map<String, Object> body) {
        long id = (Long) body.get("id");
        String name = (String) body.get("name");
        int time = (Integer) body.get("time");
        String howto = (String) body.get("howto");

        return recipesDB.updateRecipes(id, name, time, howto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/recipes/{id}")
    public boolean deleteRecipes(@PathVariable long id) {
        return recipesDB.deleteRecipes(id);
    }


}
