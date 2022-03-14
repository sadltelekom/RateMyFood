package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Rating;
import com.zeegermans.RateMyFood.model.User;

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

    public List<Rating> getRatingsById(Long ratingId) {
        String sql = "SELECT * FROM rating WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, ratingId);
            return getRatings(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Rating> getAllRatingsByRecipe(int recipeId) {
        String sql = "SELECT rating.* " +
                "FROM recipes " +
                "INNER JOIN recipes_has_rating ON recipes.id=recipes_has_rating.recipes_id " +
                "INNER JOIN rating ON recipes_has_rating.rating_id=rating.id " +
                "WHERE recipes.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, recipeId);
            return getRatings(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Long getAverageRatingByRecipe(int recipeId) {
        List<Rating> ratings = getAllRatingsByRecipe(recipeId);
        int sum = 0;
        int count = 0;

        for (Rating rating : ratings) {
            sum+=rating.getRating();
            count++;
        }

        return (long) (sum / count);
    }


    public List<Rating> getAllRatingsByUser(int recipeId) {
        String sql = "SELECT rating.* " +
                "FROM recipes " +
                "INNER JOIN recipes_has_rating ON recipes.id=recipes_has_rating.recipes_id " +
                "INNER JOIN rating ON recipes_has_rating.rating_id=rating.id " +
                "WHERE recipes.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, recipeId);
            return getRatings(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }





    // all ratings by user
    // average rating given by user
    // given rating by user of certain recipe

}
