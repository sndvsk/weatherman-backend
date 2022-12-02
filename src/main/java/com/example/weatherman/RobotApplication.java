package com.example.weatherman;

import com.example.weatherman.scheduledrobot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.text.ParseException;

@SuppressWarnings("ClassCanBeRecord")
public class RobotApplication {
    private static final Logger logger = LoggerFactory.getLogger(RobotApplication.class);
    private final Robot robot;

    public RobotApplication(Robot robot) {
        this.robot = robot;
    }

    @Scheduled(cron = "${weatherman.robot.cron_interval}")
	public void doScheduledTask() throws IOException, ParseException {
        logger.info("doScheduledTask");
		robot.scheduledTask();
	}
}
