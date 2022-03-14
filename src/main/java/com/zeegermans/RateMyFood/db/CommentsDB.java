package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
