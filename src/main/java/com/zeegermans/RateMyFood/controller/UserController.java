package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.UserDB;
import com.zeegermans.RateMyFood.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    Connection connection = DBConnector.getInstance().getConnection();

//    @CrossOrigin(origins = "*")
    @GetMapping("get/user")
    public Map<String, Object> user(@RequestParam(defaultValue = "") String name) {

        UserDB userDB = new UserDB();
        List<User> users = new ArrayList<User>() ;
        if (name.isEmpty()) {
            users = userDB.getAllUsers();
        } else {
            users = userDB.getUserByPartOfName(name);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count: ", users.size());
        result.put("results: ", users);
        List<String> usersFound = new ArrayList<>();
        for (User user : users) {
            usersFound.add("/user/" + user.getId());
        }

        result.put("results: ", usersFound);
        return result;
    }

    @GetMapping("get/user/{id}")
    public Object user(@PathVariable long id) {

        UserDB userDB = new UserDB();
        List<User> users = userDB.getUserById(id);

        if (users.isEmpty()) {
            return "No participant found with ID " + id;
        } else {
            return users;
        }
    }
}
