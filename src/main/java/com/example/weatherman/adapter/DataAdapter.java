package com.example.weatherman.adapter;

import com.example.weatherman.model.back.FormStorage;
import com.example.weatherman.model.back.Graph;
import com.example.weatherman.model.front.ApiForm;
import com.example.weatherman.model.front.CoordsForm;
import com.example.weatherman.model.front.DateForm;
import com.example.weatherman.model.front.FormApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class DataAdapter {

    public static FormStorage adaptDataFormStorage(FormApi form) {
        FormStorage formStorage = new FormStorage();

        CoordsForm coordsForm = form.getCoordsForm();
        DateForm dateForm = form.getDateForm();
        ApiForm apiForm = form.getApiForm();

        // because api round up the coordinates
        // decimal format with 6 decimal places was chosen (min round up OpenMeteo API)
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        double roundedLatitude = Double.parseDouble(decimalFormat.format(coordsForm.getLatitude()));
        double roundedLongitude = Double.parseDouble(decimalFormat.format(coordsForm.getLongitude()));

        formStorage.setLatitude(roundedLatitude);
        formStorage.setLongitude(roundedLongitude);

        formStorage.setStartDate(dateForm.getStartDate());
        formStorage.setEndDate(dateForm.getEndDate());

        formStorage.setApiNames(apiForm.getApiList());

        return formStorage;
    }

    public static String graphListToJSON(List<Graph> graphList) throws JsonProcessingException {
        return graphListToJSON(graphList, false);
    }

    public static String graphListToJSON(List<Graph> graphList, boolean pretty) throws JsonProcessingException {
        var mapper = new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (pretty) {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(graphList);
        }
        return mapper.writeValueAsString(graphList);
    }
}
