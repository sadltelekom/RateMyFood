package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.enums.categoryList;
import com.zeegermans.RateMyFood.enums.courseList;
import com.zeegermans.RateMyFood.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDB {
    private Connection connection = DBConnector.getInstance().getConnection();

    public List<Category> getCategory(PreparedStatement preparedStatement){
        List<Category> filtered = new ArrayList<>();

        try {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                Category category = new Category(
                        result.getLong("id"),
                        categoryList.valueOf(result.getString("category")),
                        courseList.valueOf(result.getString("course"))                );
                filtered.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return getCategory(preparedStatement);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

}
