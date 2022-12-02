package com.example.weatherman.scheduledrobot;

import com.example.weatherman.adapter.DataAdapter;
import com.example.weatherman.component.GraphComponent;
import com.example.weatherman.component.OpenMeteoComponent;
import com.example.weatherman.component.WeatherApiComponent;
import com.example.weatherman.component.YrnoComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Service
public class Robot {

    private final GraphComponent graphComponent;
    private final OpenMeteoComponent openMeteoComponent;
    private final WeatherApiComponent weatherApiComponent;
    private final YrnoComponent yrnoComponent;

    private final double latitude = 59.485935;
    private final double longitude = 24.860489;
    @Value("${weatherman.robot.base_path}")
    private final String basePath = "./robot_out/";

    public Robot(GraphComponent graphComponent, OpenMeteoComponent openMeteoComponent, WeatherApiComponent weatherApiComponent, YrnoComponent yrnoComponent) {
        this.graphComponent = graphComponent;
        this.openMeteoComponent = openMeteoComponent;
        this.weatherApiComponent = weatherApiComponent;
        this.yrnoComponent = yrnoComponent;
    }

    public void scheduledTask() throws IOException, ParseException {
        Date today = getTodayDate();

        yrnoComponent.deleteAllData();
        openMeteoComponent.deleteAllData();
        weatherApiComponent.deleteAllData();

        yrnoComponent.getAndFilterResponse(latitude, longitude, today, today);
        openMeteoComponent.getAndFilterResponse(latitude, longitude, today, today);
        weatherApiComponent.getAndFilterResponse(latitude, longitude, today, today);

        graphComponent.parseDataFromModels(
                yrnoComponent.getAllData(),
                openMeteoComponent.getAllData(),
                weatherApiComponent.getAllData(), "yrnoApi, openmeteoApi, weatherApi");

        var base = Path.of(basePath);
        if (Files.notExists(base)) {
            Files.createDirectory(base);
        }

        try (PrintWriter out = new PrintWriter(getNewFileName(base, "robot_task_", ".json").toString())) {
            String graphListJson = DataAdapter.graphListToJSON(graphComponent.getAllData(), true);
            out.println(graphListJson);
        }
    }

    private Date getTodayDate() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        return formatter.parse(formatter.format(today));
    }

    private Path getNewFileName(Path base, String newFilePrefix, String newFileSuffix) {
        long seed = Instant.now().toEpochMilli();
        Path newPath;
        do {
            newPath = Path.of(base.toString(), String.format("%s%d%s", newFilePrefix, seed++, newFileSuffix));
        }
        while (Files.exists(newPath));

        return newPath;
    }


}