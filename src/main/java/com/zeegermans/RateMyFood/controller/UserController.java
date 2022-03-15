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
    UserDB userDB = new UserDB();

    @CrossOrigin(origins = "*")
    @GetMapping("/user/all/")
    public List<User> users() {
        return userDB.getAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/id/{id}")
    public List<User> userById(@PathVariable long id) {
        System.out.println("got not far");
        return userDB.getUserById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/commitid/{id}")
    public List<User> userByComment(@PathVariable long id) {
        System.out.println("got not far");
        return userDB.getUserByComment(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/email/{email}")
    public List<User> userByEmail(@PathVariable String email) {
        System.out.println("got not far");
        return userDB.getUserByEmail(email);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/name/{name}")
    public List<User> userByName(@PathVariable String name) {
        System.out.println("got not far");
        return userDB.getUserByExactName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/realname/{realname}")
    public List<User> userByRealName(@PathVariable String realname) {
        System.out.println("got not far");
        return userDB.getUserByRealName(realname);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/status/{status}")
    public List<User> userByStatus(@PathVariable String status) {
        System.out.println("got not far");
        return userDB.getUserByStatus(status);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/partofname/{partofname}")
    public List<User> userBypartofname(@PathVariable String partofname) {
        System.out.println("got not far");
        return userDB.getUserByPartOfName(partofname);
    }






//    @CrossOrigin(origins = "*")
//    @GetMapping("get/user")
//    public Map<String, Object> user(@RequestParam(defaultValue = "") String name) {
//
//        UserDB userDB = new UserDB();
//        List<User> users = new ArrayList<User>() ;
//        if (name.isEmpty()) {
//            users = userDB.getAllUsers();
//        } else {
//            users = userDB.getUserByPartOfName(name);
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("count: ", users.size());
//        result.put("results: ", users);
//        List<String> usersFound = new ArrayList<>();
//        for (User user : users) {
//            usersFound.add("/user/" + user.getId());
//        }
//
//        result.put("results: ", usersFound);
//        return result;
//    }
//
//    @GetMapping("get/user/{id}")
//    public Object user(@PathVariable long id) {
//
//        UserDB userDB = new UserDB();
//        List<User> users = userDB.getUserById(id);
//
//        if (users.isEmpty()) {
//            return "No User found with ID " + id;
//        } else {
//            return users;
//        }
//    }
}
