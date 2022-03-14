package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Comments;

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

    // comments by user
    // comments by recipe
}
