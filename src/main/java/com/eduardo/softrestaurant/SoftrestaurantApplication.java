package com.eduardo.softrestaurant;

import com.eduardo.softrestaurant.presentation.SoftwareRestaurantFX;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoftrestaurantApplication {

	public static void main(String[] args) {

		//SpringApplication.run(SoftrestaurantApplication.class, args);
		Application.launch(SoftwareRestaurantFX.class, args);
	}

}
