package com.zeegermans.RateMyFood.db;

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

    // picture by user
    // picture by recipe

}
