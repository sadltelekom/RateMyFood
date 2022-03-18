package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Category;
import com.zeegermans.RateMyFood.model.Ingredients;
import com.zeegermans.RateMyFood.model.Recipes;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<String> getRecipesCategoryList( PreparedStatement preparedStatement){
        List<String> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                String outputcategory = result.getString("category");
                filtered.add(outputcategory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<String> getRecipesCourseList( PreparedStatement preparedStatement){
        List<String> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                String outputcourse = result.getString("course");
                filtered.add(outputcourse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public Map<String, String> getRecipesIngredientsList( PreparedStatement preparedStatement){
        Map<String,String> filteredMap = new HashMap<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                String name = result.getString("name");
                String amount = result.getString("amount");
                filteredMap.put(name, amount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredMap;
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

    public List<Recipes> getRecipesByExactCourse(String course) {
        String sql ="SELECT recipes.* FROM category " +
                "INNER JOIN recipes_has_category ON category.id=recipes_has_category.category_id " +
                "INNER JOIN recipes ON recipes_has_category.recipes_id=recipes.id WHERE category.course = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, course);
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

    public List<Recipes> getRecipesByPartOfCourse(String course) {
        String sql = "SELECT recipes.* FROM category " +
                "INNER JOIN recipes_has_category ON category.id=recipes_has_category.category_id " +
                "INNER JOIN recipes ON recipes_has_category.recipes_id=recipes.id WHERE category.course LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + course + "%");
            return getRecipes(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }



    public List<Recipes> getRecipesBySearch(String search) {
        List<Recipes> searchList = getRecipesByPartOfName(search);

        for (Recipes recipesingredients : getRecipesByPartOfIngredientsName(search)){
            if (searchList.contains(recipesingredients) == false){

                searchList.add(recipesingredients);
            }
        }
        for (Recipes recipescategory : getRecipesByPartOfCategory(search)){
            if (searchList.contains(recipescategory) == false){

                searchList.add(recipescategory);
            }
        }
        for (Recipes recipesusername : getRecipesByPartOfUserName(search)){
            if (searchList.contains(recipesusername) == false){
                searchList.add(recipesusername);
            }
        }
        for (Recipes recipescourse : getRecipesByPartOfCourse(search)){
            if (searchList.contains(recipescourse) == false){
                searchList.add(recipescourse);
            }
        }
        return searchList;

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


    public List<String> getRecipesCategory(long id) {
        String sql ="SELECT category.category FROM recipes " +
                "INNER JOIN recipes_has_category ON recipes.id=recipes_has_category.recipes_id " +
                "INNER JOIN category ON recipes_has_category.category_id=category.id WHERE recipes.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getRecipesCategoryList(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<String> getRecipesCourse(long id) {
        String sql ="SELECT category.course FROM recipes " +
                "INNER JOIN recipes_has_category ON recipes.id=recipes_has_category.recipes_id " +
                "INNER JOIN category ON recipes_has_category.category_id=category.id WHERE recipes.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getRecipesCourseList(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Map<String, String> getRecipesIngredients(long id) {
        String sql ="SELECT ingredients.name AS name ,recipes_has_ingredients.amount AS amount FROM recipes " +
                "INNER JOIN recipes_has_ingredients ON recipes.id = recipes_has_ingredients.recipes_id " +
                "INNER JOIN ingredients ON recipes_has_ingredients.ingredients_id = ingredients.id WHERE recipes.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getRecipesIngredientsList(preparedStatement);
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

    public List<Recipes> getRecipesByExactIngredientsName(String name) {
        String sql ="SELECT recipes.* FROM ingredients " +
                "INNER JOIN recipes_has_ingredients ON ingredients.id=recipes_has_ingredients.ingredients_id " +
                "INNER JOIN recipes ON recipes_has_ingredients.recipes_id=recipes.id WHERE ingredients.name = ? ";
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

    public List<Recipes> getRecipesByPartOfIngredientsName(String name) {
        String sql = "SELECT recipes.* FROM ingredients " +
                "INNER JOIN recipes_has_ingredients ON ingredients.id=recipes_has_ingredients.ingredients_id " +
                "INNER JOIN recipes ON recipes_has_ingredients.recipes_id=recipes.id WHERE ingredients.name LIKE ? ";
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

    public List<Recipes> getRecipesByExactIngredientsId(long id) {
        String sql = "SELECT recipes.* FROM ingredients " +
                "INNER JOIN recipes_has_ingredients ON ingredients.id=recipes_has_ingredients.ingredients_id " +
                "INNER JOIN recipes ON recipes_has_ingredients.recipes_id=recipes.id WHERE ingredients.id = ?";
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



    public boolean deleteRecipes(long id) {
        String sqlStartTransaction = "START TRANSACTION";
        String sqlCategory = "DELETE FROM recipes_has_category WHERE recipes_has_category.recipes_id = ?";
        String sqlComments = "DELETE FROM recipes_has_comments WHERE recipes_has_comments.recipes_id = ?";
        String sqlIngredients = "DELETE FROM recipes_has_ingredients WHERE recipes_has_ingredients.recipes_id = ?";
        String sqlPictures = "DELETE FROM recipes_has_picture WHERE recipes_has_picture.recipes_id = ?";
        String sqlRatings = "DELETE FROM recipes_has_rating WHERE recipes_has_rating.recipes_id = ?";
        String sqlUser = "DELETE FROM recipes_has_user WHERE recipes_has_user.recipes_id = ?";
        String sqlRecipes = "DELETE FROM recipes WHERE id = ?";
        String sqlCommitTransaction = "COMMIT";
        int affectedRows = 0;
        // first start a transaction
        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);
            PreparedStatement deleteCategory = connection.prepareStatement(sqlCategory);
            deleteCategory.setLong(1, id);
            affectedRows += deleteCategory.executeUpdate();
            PreparedStatement deleteComments = connection.prepareStatement(sqlComments);
            deleteComments.setLong(1, id);
            affectedRows += deleteComments.executeUpdate();
            PreparedStatement deleteIngredients = connection.prepareStatement(sqlIngredients);
            deleteIngredients.setLong(1, id);
            affectedRows += deleteIngredients.executeUpdate();
            PreparedStatement deletePictures = connection.prepareStatement(sqlPictures);
            deletePictures.setLong(1, id);
            affectedRows += deletePictures.executeUpdate();
            PreparedStatement deleteRating = connection.prepareStatement(sqlRatings);
            deleteRating.setLong(1, id);
            affectedRows += deleteRating.executeUpdate();
            PreparedStatement deleteUser = connection.prepareStatement(sqlUser);
            deleteUser.setLong(1, id);
            affectedRows += deleteUser.executeUpdate();
            PreparedStatement deleteRecipes = connection.prepareStatement(sqlRecipes);
            deleteRecipes.setLong(1, id);
            affectedRows += deleteRecipes.executeUpdate();
            // Commit the Changes:
            Statement commitChanges = connection.createStatement();
            commitChanges.execute(sqlCommitTransaction);
        } catch (SQLException exception) {
            exception.printStackTrace();
            try {
                String sqlRollback = "ROLLBACK";
                Statement rollbackChanges = connection.createStatement();
                rollbackChanges.execute(sqlRollback);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows != 0;
    }

    public List<Recipes> insertRecipes(String name, int time, String howto) {
        String sql = "INSERT INTO recipes VALUES (DEFAULT, ?, ? , ?)";
        long insertedId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, time);
            preparedStatement.setString(3, howto);


            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                insertedId = result.getLong(1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (insertedId != -1) {
            return getRecipesById(insertedId);
//
        } else {
            return null;
        }
    }

    public List<Recipes> updateRecipes(long id, String name, int time, String howto) {
        String sql = "UPDATE recipes SET name = ?,time = ?, howto = ? WHERE id = ?";
        int affectedRows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, time);
            preparedStatement.setString(3, howto);
            preparedStatement.setLong(4, id);

            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (affectedRows == 1) {
            return getRecipesById(id);
            // return city;
        } else {
            return null;
        }
    }





}
