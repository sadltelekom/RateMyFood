package com.zeegermans.RateMyFood.controller;


import com.zeegermans.RateMyFood.db.CommentsDB;
import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.model.Comments;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class CommentsController {

    private Connection connection = DBConnector.getInstance().getConnection();
    CommentsDB comments = new CommentsDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/comments/")
    public List<Comments> allcomments() {
        return comments.getAllComments();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/comments/{id}")
    public List<Comments> commentsById(@PathVariable long id) {
        return comments.getCommentById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/comments/recipe/{id}")
    public List<Comments> commentsByRecipe(@PathVariable long id) {
        return comments.getAllCommentsByRecipe(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/comments/user/{id}")
    public List<Comments> commentsByUser(@PathVariable long id) {
        return comments.getAllCommentsByUser(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("post/comment/")
    public List<Comments> newComment(@RequestBody Map<String, Object> body) {
        long recipeId = (Integer) body.get("recipeId");
        long userId = (Integer) body.get("userId");
        String comment = (String) body.get("comment");

        return comments.createNewComment(comment, recipeId, userId);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("update/comment/")
    public List<Comments> updateComment(@RequestBody Map<String, Object> body) {
        long commentId = (Integer) body.get("commentId");
        String newComment = (String) body.get("comment");

        return comments.updateComment(commentId, newComment);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/comment/{id}")
    public boolean deleteComment(@PathVariable long id) {
        return comments.deleteComment(id);
    }

}
