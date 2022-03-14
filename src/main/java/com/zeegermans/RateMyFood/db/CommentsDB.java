package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Rating;
import com.zeegermans.RateMyFood.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Comments> getComments(PreparedStatement preparedStatement) {
        List<Comments> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Comments comments = new Comments(
                        result.getLong("id"),
                        result.getString("comment")
                );
                filtered.add(comments);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<Comments> getAllComments() {
        String sql = "SELECT * FROM comments";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getComments(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Comments> getCommentById(Long commentId) {
        String sql = "SELECT * FROM comments WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, commentId);
            return getComments(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Comments> getAllCommentsByRecipe(long commentId) {
        String sql = "SELECT comments.* " +
                "FROM recipes " +
                "INNER JOIN recipes_has_comments ON recipes.id=recipes_has_comments.recipes_id " +
                "INNER JOIN comments ON recipes_has_comments.comments_id=comments.id " +
                "WHERE recipes.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, commentId);
            return getComments(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Comments> getAllCommentsByUser(long userId) {
        String sql = "SELECT comments.* " +
                "FROM user " +
                "INNER JOIN comments_has_user ON user.id=comments_has_user.user_id " +
                "INNER JOIN comments ON comments_has_user.comments_id=comments.id " +
                "WHERE user.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            return getComments(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    // CREATE NEW COMMENT
    public List<Comments> createNewComment(String comment, long recipeId, long userId){
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

        } catch (Exception e){
            e.printStackTrace();
        }

        if (createdId != -1){
            return getCommentById(createdId);
        }
        return null;
    }

}
