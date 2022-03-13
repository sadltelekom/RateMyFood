package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Rating> getRatings(PreparedStatement preparedStatement){
        List<Rating> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Rating rating = new Rating(
                        result.getLong("id"),
                        result.getInt("rating")

                );
                filtered.add(rating);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<Rating> getAllRatings() {
        String sql = "SELECT * FROM rating";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getRatings(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    // ratings by recipe
    // ratings by user

}
