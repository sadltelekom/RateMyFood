package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Ingredients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Ingredients> getIngredients(PreparedStatement preparedStatement){
        List<Ingredients> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Ingredients ingredients = new Ingredients(
                        result.getLong("id"),
                        result.getString("name")
                );
                filtered.add(ingredients);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<Ingredients> getAllIngredients() {
        String sql = "SELECT * FROM ingredients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getIngredients(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Ingredients> getIngredientById(long id) {
        String sql = "SELECT * FROM ingredients WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return getIngredients(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Ingredients> getIngredientByRecipeId(long id) {
        String sql = "SELECT ingredients.id AS id,ingredients.name AS name ,recipes_has_ingredients.amount AS amount FROM recipes INNER JOIN recipes_has_ingredients ON recipes.id = recipes_has_ingredients.recipes_id INNER JOIN ingredients ON recipes_has_ingredients.ingredients_id = ingredients.id WHERE recipes.id = ?";
        List<Ingredients> filtered = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

                while(result.next()) {
                    Ingredients ingredients = new Ingredients(
                            result.getLong("id"),
                            result.getString("name")
                    );
                    ingredients.setAmount(result.getString("amount"));
                    filtered.add(ingredients);
                }

        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return filtered;
    }


    public List<Ingredients> getIngredientByExactName(String name) {
        String sql = "SELECT * FROM ingredients WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            return getIngredients(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Ingredients> getIngredientByPartOfName(String name) {
        String sql = "SELECT * FROM ingredients WHERE name LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            return getIngredients(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean deleteIngredients(long id) {
        String sqlStartTransaction = "START TRANSACTION";
        String sqlRecipes = "DELETE FROM recipes_has_ingredients WHERE recipes_has_ingredients.ingredients_id = ?";
        String sqlIngredients = "DELETE FROM ingredients WHERE id = ?";
        String sqlCommitTransaction = "COMMIT";
        int affectedRows = 0;
        // first start a transaction
        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);
            PreparedStatement deleteRecipes = connection.prepareStatement(sqlRecipes);
            deleteRecipes.setLong(1, id);
            affectedRows += deleteRecipes.executeUpdate();
            PreparedStatement deleteIngredients = connection.prepareStatement(sqlIngredients);
            deleteIngredients.setLong(1, id);
            affectedRows += deleteIngredients.executeUpdate();
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

    public List<Ingredients> insertIngredients(String name) {
        String sql = "INSERT INTO ingredients VALUES (DEFAULT, ?)";
        long insertedId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);


            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                insertedId = result.getLong(1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (insertedId != -1) {
            return getIngredientById(insertedId);
//
        } else {
            return null;
        }
    }

    public List<Ingredients> updateIngredients(long id, String name) {
        String sql = "UPDATE Ingredients SET Name = ? WHERE id = ?";
        int affectedRows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (affectedRows == 1) {
            return getIngredientById(id);
            // return city;
        } else {
            return null;
        }
    }

    public void insertIngredientToRecipe(Ingredients ingredient, long recipeId) {
        String sql = "INSERT INTO recipes_has_ingredients VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, recipeId);
            preparedStatement.setLong(2,ingredient.getId());
            preparedStatement.setString(3,ingredient.getAmount());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ingredients ...
    }
}
