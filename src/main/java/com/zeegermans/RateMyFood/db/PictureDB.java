package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Picture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictureDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Picture> getPicture(PreparedStatement preparedStatement){
        List<Picture> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Picture picture = new Picture(
                        result.getLong("id"),
                        result.getString("link")

                );
                filtered.add(picture);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<Picture> getAllPicture() {
        String sql = "SELECT * FROM picture";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getPicture(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Picture> getPictureById(long pictureId) {
        String sql = "SELECT * FROM picture WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, pictureId);
            return getPicture(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Picture> getAllPicturesByRecipe(long recipeId) {
        String sql = "SELECT picture.* " +
                "FROM recipes " +
                "INNER JOIN recipes_has_picture ON recipes.id=recipes_has_picture.recipes_id " +
                "INNER JOIN picture ON recipes_has_picture.picture_id=picture.id " +
                "WHERE recipes.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, recipeId);
            return getPicture(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Picture> getAllPicturesByUser(long userId) {
        String sql = "SELECT picture.* " +
                "FROM user " +
                "INNER JOIN picture_has_user ON user.id=picture_has_user.user_id " +
                "INNER JOIN picture ON picture_has_user.picture_id=picture.id " +
                "WHERE user.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            return getPicture(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }



    // picture by id
    // picture by user
    // picture by recipe
    // new picture/link
    // no update
    // delete picture

}
