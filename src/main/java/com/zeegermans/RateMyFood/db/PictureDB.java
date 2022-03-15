package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Picture;

import java.sql.*;
import java.time.LocalDateTime;
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
    public List<Picture> createNewComment(String comment, long recipeId, long userId){
        String sqlStartTransaction ="START TRANSACTION";
        String sqlComment = "INSERT INTO comments VALUES (DEFAULT, ?)";
        String sqlComment2Recipe = "INSERT INTO recipes_has_comments VALUES (?, ?)"; // -- recipe_id, comment_id
        String sqlComment2User = "INSERT INTO comments_has_user VALUES (?, ?)"; // -- comment_id, user_id
        String sqlCommitTransaction = "COMMIT";
        long createdId = -1;

        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);

            PreparedStatement newComment = connection.prepareStatement(sqlComment, Statement.RETURN_GENERATED_KEYS);
            newComment.setString(1,comment);
            newComment.executeUpdate();

            ResultSet result = newComment.getGeneratedKeys();

            if (result.next()){
                createdId = result.getLong(1);
            }

            PreparedStatement comment2Recipe = connection.prepareStatement(sqlComment2Recipe);
            comment2Recipe.setLong(1,recipeId);
            comment2Recipe.setLong(2,createdId);
            comment2Recipe.executeUpdate();

            PreparedStatement comment2User = connection.prepareStatement(sqlComment2User);
            comment2User.setLong(1,createdId);
            comment2User.setLong(2,userId);
            comment2User.executeUpdate();

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
//            return getCommentById(createdId);
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
