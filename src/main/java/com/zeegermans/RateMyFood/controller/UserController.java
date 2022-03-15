package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.UserDB;
import com.zeegermans.RateMyFood.model.Rating;
import com.zeegermans.RateMyFood.model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private Connection connection = DBConnector.getInstance().getConnection();
    UserDB userDB = new UserDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/allusers/")
    public List<User> users() {
        return userDB.getAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/id/{id}")
    public List<User> userById(@PathVariable long id) {
        return userDB.getUserById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/commentid/{id}")
    public List<User> userByCommentId(@PathVariable long id) {
        return userDB.getUserByComment(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/recipeid/{id}")
    public List<User> userByRecipeId(@PathVariable long id) {
        return userDB.getUserByRecipeId(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/recipename/{name}")
    public List<User> userByRecipeName(@PathVariable String name) {
        return userDB.getUserByRecipeName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/email/{email}")
    public List<User> userByEmail(@PathVariable String email) {
        return userDB.getUserByEmail(email);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/name/{name}")
    public List<User> userByName(@PathVariable String name) {
        return userDB.getUserByExactName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/realname/{realname}")
    public List<User> userByRealName(@PathVariable String realname) {
        return userDB.getUserByRealName(realname);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/status/{status}")
    public List<User> userByStatus(@PathVariable String status) {
        return userDB.getUserByStatus(status);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/user/partofname/{partofname}")
    public List<User> userByPartOfName(@PathVariable String partofname) {
        return userDB.getUserByPartOfName(partofname);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("post/user/")
    public List<User> newUser(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        String realName = (String) body.get("realname");
        String status = (String) body.get("status");
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        return userDB.createNewUser(name, realName, status, email, password);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("update/user/")
    public List<User> updateUser(@RequestBody Map<String, Object> body) {
        long id = (Long) body.get("id");
        String name = (String) body.get("name");
        String realName = (String) body.get("realname");
        String status = (String) body.get("status");
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        return userDB.updateUser(id, name, realName, status, email, password);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/user/{id}")
    public boolean deleteUser(@PathVariable long id) {
        return userDB.deleteUser(id);
    }


    @CrossOrigin(origins = "*")
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
            return "No User found with ID " + id;
        } else {
            return users;
        }
    }
}
