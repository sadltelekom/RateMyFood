package com.zeegermans.RateMyFood;

import com.zeegermans.RateMyFood.db.CommentsDB;
import com.zeegermans.RateMyFood.db.DBConnector;
import com.zeegermans.RateMyFood.db.RatingDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication(scanBasePackages = {"com.zeegermans.RateMyFood.controller"})
public class RateMyFoodApplication {

	public static void main(String[] args) {
		Connection connection = DBConnector.getInstance().getConnection();

//		CommentsDB comment = new CommentsDB();
//		System.out.println(comment.updateComment(12, "das ist ein kommentar Update"));

		SpringApplication.run(RateMyFoodApplication.class, args);
	}

}
