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

}
