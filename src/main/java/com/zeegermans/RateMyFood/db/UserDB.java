package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<User> getUsers( PreparedStatement preparedStatement){
        List<User> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                User user = new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("realname"),
                        result.getString("status"),
                        result.getString("email"),
                        result.getString("password")
                );
                filtered.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByExactName(String name) {
        String sql = "SELECT * FROM user WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByPartOfName(String name) {
        String sql = "SELECT * FROM user WHERE name LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByRealName(String realname) {
        String sql = "SELECT * FROM user WHERE realname=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, realname);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByStatus(String status) {
        String sql = "SELECT * FROM user WHERE status=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByRecipe(int recipeId) {
        String sql = "SELECT user.* " +
                    "FROM recipes " +
                    "INNER JOIN recipes_has_user ON recipes.id=recipes_has_user.recipes_id " +
                    "INNER JOIN user ON recipes_has_user.user_id=user.id " +
                    "WHERE recipes.id=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, recipeId);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getUserByComment(int commentId) {
        String sql = "SELECT user.* " +
                "FROM comments " +
                "INNER JOIN comments_has_user ON comments.id=comments_has_user.comments_id " +
                "INNER JOIN user ON comments_has_user.user_id=user.id " +
                "WHERE comments.id=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, commentId);
            return getUsers(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }





    // create user





}
