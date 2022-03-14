package com.zeegermans.RateMyFood;

import com.zeegermans.RateMyFood.db.CommentsDB;
import com.zeegermans.RateMyFood.db.DBConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication(scanBasePackages = {"com.zeegermans.RateMyFood.controller"})
public class RateMyFoodApplication {

	public static void main(String[] args) {
		Connection connection = DBConnector.getInstance().getConnection();

		CommentsDB comment = new CommentsDB();
		System.out.println(comment.createNewComment("blabla wieder neuer Kommentar", 5l, 4l));

		SpringApplication.run(RateMyFoodApplication.class, args);
	}

}
