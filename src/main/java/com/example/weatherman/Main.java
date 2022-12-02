package com.example.weatherman;

import org.springframework.boot.SpringApplication;

public class Main {
    public static void main(String[] args) {
        boolean isRobot = System.getenv().containsKey("weatherman.robot.cron_interval")
                && System.getenv().get("weatherman.robot.cron_interval").compareToIgnoreCase("0 0 5 31 2 ?") != 0;
        if (isRobot) {
            SpringApplication.run(RobotApplication.class, args);
        } else {
            SpringApplication.run(WeathermanApplication.class, args);
        }
    }
}
