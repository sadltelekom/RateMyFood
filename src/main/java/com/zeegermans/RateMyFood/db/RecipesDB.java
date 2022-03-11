package com.zeegermans.RateMyFood.db;

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

}
