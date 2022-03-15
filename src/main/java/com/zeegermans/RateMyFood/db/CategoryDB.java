package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.enums.CategoryList;
import com.zeegermans.RateMyFood.enums.CourseList;
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
                        CategoryList.valueOf(result.getString("category")),
                        CourseList.valueOf(result.getString("course"))                );
                filtered.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public Object getAllCategories() {
        return CategoryList.values();
//        String sql = "SELECT * FROM category";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            return getCategory(preparedStatement);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public Object getAllCourses() {
        return CourseList.values();
    }

}
