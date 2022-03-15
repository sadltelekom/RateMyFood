package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Ingredients;
import com.zeegermans.RateMyFood.model.Recipes;

import java.sql.*;
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
        String sql = "INSERT INTO ingredients VALUES (DEFAULT, ?, ? , ?)";
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

    public List<Recipes> updateRecipesName(Recipes recipes) {
        String sql = "UPDATE Ingredients SET name = ?,time = ?, howto = ? WHERE id = ?";
        int affectedRows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, recipes.getName());
            preparedStatement.setInt(2, recipes.getTime());
            preparedStatement.setString(3, recipes.getHowto());
            preparedStatement.setLong(4, recipes.getId());

            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (affectedRows == 1) {
            return getRecipesById(recipes.getId());
            // return city;
        } else {
            return null;
        }
    }


    // recipe by category
    // recipe by user
    // recipe by ingredients
    // recipe by rating

}
