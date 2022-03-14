package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Ingredients;
import com.zeegermans.RateMyFood.model.Recipes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipesDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Recipes> getRecipes( PreparedStatement preparedStatement){
        List<Recipes> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Recipes recipe = new Recipes(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getInt("time"),
                        result.getString("howto")
                );
                filtered.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<Recipes> getAllRecipes() {
        String sql = "SELECT * FROM recipes";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesById(long id) {
        String sql = "SELECT * FROM recipes WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }




    public List<Recipes> getRecipesByExactName(String name) {
        String sql = "SELECT * FROM recipes WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByPartOfName(String name) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByExactCategory(String category) {
        String sql ="SELECT recipes.* FROM category " +
                "INNER JOIN recipes_has_category ON category.id=recipes_has_category.category_id " +
                "INNER JOIN recipes ON recipes_has_category.recipes_id=recipes.id WHERE category.category = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByPartOfCategory(String category) {
        String sql = "SELECT recipes.* FROM category " +
                "INNER JOIN recipes_has_category ON category.id=recipes_has_category.category_id " +
                "INNER JOIN recipes ON recipes_has_category.recipes_id=recipes.id WHERE category.category LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + category + "%");
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByExactCategoryId(long id) {
        String sql ="SELECT recipes.* FROM category " +
                "INNER JOIN recipes_has_category ON category.id=recipes_has_category.category_id " +
                "INNER JOIN recipes ON recipes_has_category.recipes_id=recipes.id WHERE category.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByExactUserName(String username) {
        String sql ="SELECT recipes.* FROM user " +
                "INNER JOIN recipes_has_user ON user.id=recipes_has_user.user_id " +
                "INNER JOIN recipes ON recipes_has_user.recipes_id=recipes.id WHERE user.name = ? OR user.realname = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByPartOfUserName(String username) {
        String sql = "SELECT recipes.* FROM user " +
                "INNER JOIN recipes_has_user ON user.id=recipes_has_user.user_id " +
                "INNER JOIN recipes ON recipes_has_user.recipes_id=recipes.id WHERE user.name LIKE ? OR user.realname LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + username + "%");
            preparedStatement.setString(2, "%" + username + "%");
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Recipes> getRecipesByExactUserId(long id) {
        String sql ="SELECT recipes.* FROM user " +
                "INNER JOIN recipes_has_user ON user.id=recipes_has_user.user_id " +
                "INNER JOIN recipes ON recipes_has_user.recipes_id=recipes.id WHERE user.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }





    // recipe by category
    // recipe by user
    // recipe by ingredients
    // recipe by rating

}
