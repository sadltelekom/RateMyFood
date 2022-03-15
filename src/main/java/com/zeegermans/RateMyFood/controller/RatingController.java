package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.CommentsDB;
import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.RatingDB;
import com.zeegermans.RateMyFood.model.Comments;
import com.zeegermans.RateMyFood.model.Rating;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class RatingController {

        private Connection connection = DBConnector.getInstance().getConnection();
        RatingDB rating = new RatingDB();

        @CrossOrigin(origins = "*")
        @GetMapping("get/ratings/")
        public List<Rating> allRatings() {
            return rating.getAllRatings();
        }

        @CrossOrigin(origins = "*")
        @GetMapping("get/ratings/{id}")
        public List<Rating> ratingsById(@PathVariable long id) {
            return rating.getRatingsById(id);
        }

        @CrossOrigin(origins = "*")
        @GetMapping("get/ratings/recipe/{id}")
        public List<Rating> ratingsByRecipe(@PathVariable long id) {
            return rating.getAllRatingsByRecipe(id);
        }

        @CrossOrigin(origins = "*")
        @GetMapping("get/ratings/recipeaverage/{id}")
        public Long averageRatingsRecipe(@PathVariable long id) {
        return rating.getAverageRatingByRecipe(id);
        }

        @CrossOrigin(origins = "*")
        @GetMapping("get/ratings/user/{id}")
        public List<Rating> ratingsByUser(@PathVariable long id) {
            return rating.getAllRatingsByUser(id);
        }

        @CrossOrigin(origins = "*")
        @GetMapping("get/ratings/useraverage/{id}")
        public Long averageRatingsUser(@PathVariable long id) {
        return rating.getAverageRatingByUser(id);
        }

        @CrossOrigin(origins = "*")
        @PostMapping("post/rating/")
        public List<Rating> newRating(@RequestBody Map<String, Object> body) {
            long recipeId = (Integer) body.get("recipeId");
            long userId = (Integer) body.get("userId");
            long userRating = (Integer) body.get("rating");

            return rating.createNewRating(userRating, recipeId, userId);
        }

        @CrossOrigin(origins = "*")
        @PatchMapping("update/rating/")
        public List<Rating> updateRating(@RequestBody Map<String, Object> body) {
            long ratingId = (Integer) body.get("ratingId");
            long newRating = (Integer) body.get("rating");

            return rating.updateRating(ratingId, newRating);
        }

        @CrossOrigin(origins = "*")
        @DeleteMapping("delete/ratings/{id}")
        public boolean deleteRating(@PathVariable long id) {
            return rating.deleteRating(id);
        }

    }
