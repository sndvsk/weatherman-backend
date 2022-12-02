package com.example.weatherman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeathermanApplication {
	public static void main(String[] args) {
		var isRobot = args.length != 0 &&
				(args[0].compareToIgnoreCase("robot") == 0 || args[0].compareToIgnoreCase("-robot") == 0);

		if (isRobot) {
			SpringApplication.run(RobotApplication.class, args);
		} else {
			SpringApplication.run(WeathermanApplication.class, args);
		}
	}
}
