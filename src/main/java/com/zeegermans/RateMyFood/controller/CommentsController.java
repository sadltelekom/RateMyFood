package com.zeegermans.RateMyFood.controller;


import com.zeegermans.RateMyFood.db.CommentsDB;
import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.model.Comments;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

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
    @


}
