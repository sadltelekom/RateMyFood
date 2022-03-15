package com.zeegermans.RateMyFood;

import com.zeegermans.RateMyFood.db.*;
import com.zeegermans.RateMyFood.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication(scanBasePackages = {"com.zeegermans.RateMyFood.controller"})
public class RateMyFoodApplication {

	public static void main(String[] args) {
		Connection connection = DBConnector.getInstance().getConnection();

		PictureDB comment = new PictureDB();
		System.out.println(comment.createNewPicture("/images/pic_5_1647383729584.jpg", 3,2));

		SpringApplication.run(RateMyFoodApplication.class, args);
	}

}
