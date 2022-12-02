package com.example.weatherman.controller;

import com.example.weatherman.adapter.DataAdapter;
import com.example.weatherman.component.GraphComponent;
import com.example.weatherman.component.OpenMeteoComponent;
import com.example.weatherman.component.WeatherApiComponent;
import com.example.weatherman.component.YrnoComponent;
import com.example.weatherman.model.back.FormStorage;
import com.example.weatherman.model.back.Graph;
import com.example.weatherman.model.front.FormApi;
import com.example.weatherman.repository.FormRepository;
import com.example.weatherman.scheduledrobot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FormController {
    private static Logger logger = LoggerFactory.getLogger(FormController.class);
    private final FormRepository formRepository;

    private final YrnoComponent yrnoComponent;
    private final OpenMeteoComponent openMeteoComponent;
    private final WeatherApiComponent weatherApiComponent;
    private final GraphComponent graphComponent;

    private final Robot robot;

    public FormController(FormRepository formRepository,
                          YrnoComponent yrnoComponent,
                          OpenMeteoComponent openMeteoComponent,
                          WeatherApiComponent weatherApiComponent,
                          GraphComponent graphComponent,
                          Robot robot
                        ) {
        this.formRepository = formRepository;
        this.yrnoComponent = yrnoComponent;
        this.openMeteoComponent = openMeteoComponent;
        this.weatherApiComponent = weatherApiComponent;
        this.graphComponent = graphComponent;
        this.robot = robot;
    }

    @DeleteMapping("/forms")
    public void deleteForms() {
        formRepository.deleteAll();
    }

    @GetMapping("/forms")
    public List<FormStorage> getForms() {
        return formRepository.findAll().isEmpty() ? Collections.emptyList() : formRepository.findAll();
    }

    @PostMapping("/forms")
    ResponseEntity<List<Graph>> addForm(@RequestBody FormApi form) throws ParseException {
        // ApiForm, CoordsForm, DateForm -> FormApi
        // FormApi -> FormStorage
        // take data from FormStorage
        // if contains in FormStorage.api_names from [yrno, openmeteo, weatherapi]
        // request the apis that were chosen
        // save parsed response or responses to respective tables (time and temperatures)
        // send parsed responses to angular client (front)
        FormStorage formStorage = DataAdapter.adaptDataFormStorage(form);
        formRepository.save(formStorage);

        yrnoComponent.deleteAllData();
        openMeteoComponent.deleteAllData();
        weatherApiComponent.deleteAllData();

        if (formStorage.getApiNames().contains("yrnoApi")) {
            yrnoComponent.getAndFilterResponse(
                    formStorage.getLatitude(),
                    formStorage.getLongitude(),
                    formStorage.getStartDate(),
                    formStorage.getEndDate());
        }
        if (formStorage.getApiNames().contains("openmeteoApi")) {
            openMeteoComponent.getAndFilterResponse(
                    formStorage.getLatitude(),
                    formStorage.getLongitude(),
                    formStorage.getStartDate(),
                    formStorage.getEndDate());
        }
        if (formStorage.getApiNames().contains("weatherApi")) {
            weatherApiComponent.getAndFilterResponse(
                    formStorage.getLatitude(),
                    formStorage.getLongitude(),
                    formStorage.getStartDate(),
                    formStorage.getEndDate());
        }
        if (!formStorage.getApiNames().isEmpty()) {
            graphComponent.parseDataFromModels(
                    yrnoComponent.getAllData(),
                    openMeteoComponent.getAllData(),
                    weatherApiComponent.getAllData(),
                    formStorage.getApiNames());
        }

        var respObj = graphComponent.getAllData();
        return new ResponseEntity<>(respObj, HttpStatus.OK);
    }
}
