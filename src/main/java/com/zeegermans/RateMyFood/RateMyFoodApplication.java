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
		SpringApplication.run(RateMyFoodApplication.class, args);
	}

}
