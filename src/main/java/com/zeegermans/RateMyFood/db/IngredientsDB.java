package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.enums.ingredientsList;
import com.zeegermans.RateMyFood.model.Ingredients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    // ingredients ...

}
