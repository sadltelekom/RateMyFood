package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Rating> getAllRatingsByRecipe(long recipeId) {
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

    public List<Rating> getAllRatingsByUser(long userId) {
        String sql = "SELECT rating.* " +
                "FROM user " +
                "INNER JOIN user_has_rating ON user.id=user_has_rating.user_id " +
                "INNER JOIN rating ON user_has_rating.rating_id=rating.id " +
                "WHERE user.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            return getRatings(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Map getAverageRatingByRecipe(long recipeId) {
        List<Rating> ratings = getAllRatingsByRecipe(recipeId);
        Map<String, Long> average = new HashMap<>();
        average.put("average", calcAverage(ratings));
        return average;
    }

    public Map getAverageRatingByUser(long userId) {
        List<Rating> ratings = getAllRatingsByRecipe(userId);
        Map<String, Long> average = new HashMap<>();
        average.put("average", calcAverage(ratings));
        return average;
    }

    public Long calcAverage (List<Rating> ratings) {
        int sum = 0;
        int count = 0;

        if (ratings.isEmpty()) {
            return 0l;
        }

        for (Rating rating : ratings) {
            sum+=rating.getRating();
            count++;
        }

        return (long)(sum / count);
    }

    // CREATE NEW RATING
    public List<Rating> createNewRating(long rating, long recipeId, long userId){
        String sqlStartTransaction ="START TRANSACTION";
        String sqlRating = "INSERT INTO rating VALUES (DEFAULT, ?)";
        String sqlRating2Recipe = "INSERT INTO recipes_has_rating VALUES (?, ?)"; // -- recipe_id, rating_id
        String sqlRating2User = "INSERT INTO user_has_rating VALUES (?, ?)"; // -- user_id, rating_id
        String sqlCommitTransaction = "COMMIT";
        long createdId = -1;

        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);

            PreparedStatement newRating = connection.prepareStatement(sqlRating, Statement.RETURN_GENERATED_KEYS);
            newRating.setLong(1,rating);
            newRating.executeUpdate();

            ResultSet result = newRating.getGeneratedKeys();

            if (result.next()){
                createdId = result.getLong(1);
            }

            PreparedStatement rating2Recipe = connection.prepareStatement(sqlRating2Recipe);
            rating2Recipe.setLong(1,recipeId);
            rating2Recipe.setLong(2,createdId);
            rating2Recipe.executeUpdate();

            PreparedStatement rating2User = connection.prepareStatement(sqlRating2User);
            rating2User.setLong(1,userId);
            rating2User.setLong(2,createdId);
            rating2User.executeUpdate();

            Statement commitChanges = connection.createStatement();
            commitChanges.execute(sqlCommitTransaction);

        } catch (SQLException exception){
            exception.printStackTrace();
            try {
                String sqlRollback = "ROLLBACK";
                Statement rollbackChanges = connection.createStatement();
                rollbackChanges.execute(sqlRollback);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (createdId != -1){
            return getRatingsById(createdId);
        }
        return null;
    }

    // DELETE RATING
    public boolean deleteRating(long ratingId){
        String sqlStartTransaction ="START TRANSACTION";
        String sqlRating = "DELETE FROM rating WHERE id=?";
        String sqlRating2Recipe = "DELETE FROM recipes_has_rating WHERE recipes_has_rating.rating_id=?";
        String sqlRating2User = "DELETE FROM user_has_rating WHERE user_has_rating.rating_id=?";
        String sqlCommitTransaction = "COMMIT";
        int affectedRows = 0;

        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);

            PreparedStatement deleteRating2Recipe = connection.prepareStatement(sqlRating2Recipe);
            deleteRating2Recipe.setLong(1,ratingId);
            affectedRows+= deleteRating2Recipe.executeUpdate();

            PreparedStatement deleteRating2User = connection.prepareStatement(sqlRating2User);
            deleteRating2User.setLong(1,ratingId);
            affectedRows+= deleteRating2User.executeUpdate();

            PreparedStatement deleteRating = connection.prepareStatement(sqlRating);
            deleteRating.setLong(1,ratingId);
            affectedRows+= deleteRating.executeUpdate();

            Statement commitChanges = connection.createStatement();
            commitChanges.execute(sqlCommitTransaction);

        } catch (SQLException exception){
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

    // UPDATE RATING
    public List<Rating> updateRating (long ratingId, long newRating) {
        String sql = "UPDATE rating SET rating=? WHERE id=? ";
        long rowsAffected = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, newRating);
            preparedStatement.setLong(2, ratingId);
            rowsAffected = preparedStatement.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }

        if (rowsAffected == 1){
            return getRatingsById(ratingId);
        } else
            return null;

    }

}
