package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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

    //CREATE NEW PICTURE NAME
    public String createNewPictureName () {
        String sql = "SELECT * FROM picture ORDER BY id DESC LIMIT 1";
        long lastId = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            List<Picture> picture = getPicture(preparedStatement);
            for (Picture e : picture) {
                lastId = e.getId();
            }
            lastId++;
            return "pic_" + lastId + "_" + Calendar.getInstance().getTimeInMillis();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    // CREATE NEW PICTURE LINK
    public List<Picture> createNewPicture(String link, long recipeId, long userId){
        String sqlStartTransaction ="START TRANSACTION";
        String sqlPicture = "INSERT INTO picture VALUES (DEFAULT, ?)";
        String sqlPicture2Recipe = "INSERT INTO recipes_has_picture VALUES (?, ?)"; // -- recipe_id, picture_id
        String sqlPicture2User = "INSERT INTO picture_has_user VALUES (?, ?)"; // -- picture_id, user_id
        String sqlCommitTransaction = "COMMIT";
        long createdId = -1;

        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);

            PreparedStatement newPicture = connection.prepareStatement(sqlPicture, Statement.RETURN_GENERATED_KEYS);
            newPicture.setString(1,link);
            newPicture.executeUpdate();

            ResultSet result = newPicture.getGeneratedKeys();

            if (result.next()){
                createdId = result.getLong(1);
            }

            PreparedStatement picture2Recipe = connection.prepareStatement(sqlPicture2Recipe);
            picture2Recipe.setLong(1,recipeId);
            picture2Recipe.setLong(2,createdId);
            picture2Recipe.executeUpdate();

            PreparedStatement picture2User = connection.prepareStatement(sqlPicture2User);
            picture2User.setLong(1,createdId);
            picture2User.setLong(2,userId);
            picture2User.executeUpdate();

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
            return getPictureById(createdId);
        }
        return null;
    }

    // DELETE PICTURE LINK
    public boolean deletePicture(long pictureId){
        String sqlStartTransaction ="START TRANSACTION";
        String sqlPicture = "DELETE FROM picture WHERE id=?";
        String sqlPicture2Recipe = "DELETE FROM recipes_has_picture WHERE recipes_has_picture.picture_id=?";
        String sqlPicture2User = "DELETE FROM picture_has_user WHERE picture_has_user.picture_id=?";
        String sqlCommitTransaction = "COMMIT";
        int affectedRows = 0;

        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);

            PreparedStatement deletePicture2Recipe = connection.prepareStatement(sqlPicture2Recipe);
            deletePicture2Recipe.setLong(1,pictureId);
            affectedRows+= deletePicture2Recipe.executeUpdate();

            PreparedStatement deletePicture2User = connection.prepareStatement(sqlPicture2User);
            deletePicture2User.setLong(1,pictureId);
            affectedRows+= deletePicture2User.executeUpdate();

            PreparedStatement deletePicture = connection.prepareStatement(sqlPicture);
            deletePicture.setLong(1,pictureId);
            affectedRows+= deletePicture.executeUpdate();

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

}
