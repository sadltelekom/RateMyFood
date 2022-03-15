package com.zeegermans.RateMyFood.db;

import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Rating;
import com.zeegermans.RateMyFood.model.Recipes;
import com.zeegermans.RateMyFood.model.User;

import java.sql.*;
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

    public List<User> getUserById (long userId) {
        String sql = "SELECT * FROM user WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
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

    public List<User> getUserByRecipe(long recipeId) {
        String sql = "SELECT user.* " +
                    "FROM recipes " +
                    "INNER JOIN recipes_has_user ON recipes.id=recipes_has_user.recipes_id " +
                    "INNER JOIN user ON recipes_has_user.user_id=user.id " +
                    "WHERE recipes.id=?";
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

    public List<User> getUserByComment(long commentId) {
        String sql = "SELECT user.* " +
                "FROM comments " +
                "INNER JOIN comments_has_user ON comments.id=comments_has_user.comments_id " +
                "INNER JOIN user ON comments_has_user.user_id=user.id " +
                "WHERE comments.id=?";
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

    // CREATE NEW USER
    public List<User> createNewUser(String name, String realName, String status, String email, String password){
        String sql = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        long createdId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);
            preparedStatement.setString(1,realName);
            preparedStatement.setString(1,status);
            preparedStatement.setString(1,email);
            preparedStatement.setString(1,password);

            ResultSet result = preparedStatement.getGeneratedKeys();

            if (result.next()){
                createdId = result.getLong(1);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        if (createdId != -1){
            return getUserById(createdId);
        }
        return null;
    }

    // UPDATE EXISTING USER
    public List<User> updateUser(User user){
        String sql = "UPDATE user SET name =?, realname =?, status =?, email =?, password =? WHERE id =?";
        long rowsAffected = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRealName());
            preparedStatement.setString(3, user.getStatus());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            rowsAffected = preparedStatement.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }

        if (rowsAffected == 1){
            return getUserById(user.getId());
        } else
            return null;
    }

    // DELETE USER
    public boolean deleteUser(long userId){
        String sqlStartTransaction ="START TRANSACTION";
        String sqlUser = "DELETE FROM user WHERE id=?";
        String sqlCommitTransaction = "COMMIT";
        int affectedRows = 0;

        CommentsDB comments = new CommentsDB();
        List<Comments> userComments = comments.getAllCommentsByUser(userId);
        for (Comments comment : userComments) {
            comments.deleteComment(comment.getId());
        }

        RatingDB ratings = new RatingDB();
        List<Rating> userRatings = ratings.getAllRatingsByUser(userId);
        for (Rating rating : userRatings) {
            ratings.deleteRating(rating.getId());
        }

        RecipesDB recipes = new RecipesDB();
        List<Recipes> userRecipes = recipes.getRecipesByExactUserId(userId);
        for (Recipes recipe : userRecipes) {
            recipes.deleteRecipes(recipe.getId());
        }

        try {
            Statement startTransaction = connection.createStatement();
            startTransaction.execute(sqlStartTransaction);

            PreparedStatement deleteRating = connection.prepareStatement(sqlUser);
            deleteRating.setLong(1,userId);
            affectedRows+= deleteRating.executeUpdate();

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
