package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.PictureDB;
import com.zeegermans.RateMyFood.model.Picture;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class PictureController {

    private Connection connection = DBConnector.getInstance().getConnection();
    PictureDB picture = new PictureDB();

    @CrossOrigin(origins = "*")
    @GetMapping("get/pictures/")
    public List<Picture> allpictures() {
        return picture.getAllPicture();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/pictures/{id}")
    public List<Picture> picturesById(@PathVariable long id) {
        return picture.getPictureById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/pictures/recipe/{id}")
    public List<Picture> picturesByRecipe(@PathVariable long id) {
        return picture.getAllPicturesByRecipe(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/pictures/user/{id}")
    public List<Picture> picturesByUser(@PathVariable long id) {
        return picture.getAllPicturesByUser(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("get/pictures/uploadname/")
    public String uploadPictureName() {
        return picture.createNewPictureName();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("post/pictures/")
    public List<Picture> newPicture(@RequestBody Map<String, Object> body) {
        long recipeId = (Integer) body.get("recipeId");
        long userId = (Integer) body.get("userId");
        String link = (String) body.get("link");

        return picture.createNewPicture(link, recipeId, userId);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/pictures/{id}")
    public boolean deletePicture(@PathVariable long id) {
        return picture.deletePicture(id);
    }

}
