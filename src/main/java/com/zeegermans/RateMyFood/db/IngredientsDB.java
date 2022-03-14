package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.enums.ingredientsList;
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
                        ingredientsList.valueOf(result.getString("name"))
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
    public boolean deleteIngredientById(long id) {
        String sql = "DELETE FROM ingredients WHERE id = ?";
        int affectedRows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return affectedRows == 1;
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

    public List<Ingredients> updateIngredients(Ingredients ingredients) {
        String sql = "UPDATE Ingredients SET Name = ? WHERE id = ?";
        int affectedRows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ingredients.getName().toString());
            preparedStatement.setLong(2, ingredients.getId());

            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (affectedRows == 1) {
            return getIngredientById(ingredients.getId());
            // return city;
        } else {
            return null;
        }
    }


    // ingredients ...

}
