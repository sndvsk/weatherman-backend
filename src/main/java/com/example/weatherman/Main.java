package com.example.weatherman;

import org.springframework.boot.SpringApplication;

public class Main {
    public static void main(String[] args) {
        boolean isRobot = System.getenv().containsKey("weatherman.robot.cron_interval");
        if (isRobot) {
            SpringApplication.run(RobotApplication.class, args);
        } else {
            SpringApplication.run(WeathermanApplication.class, args);
        }
    }
}
