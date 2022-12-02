package com.example.weatherman.service;

import com.example.weatherman.dto.OpenMeteoDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

// idea was takes from here
// https://github.com/saadusufzai/WeatherApp/blob/master/src/main/java/com/bwap/weatherapp/WeatherApp/controller/WeatherService.java
@Service
public class OpenMeteoService {
    private static final Logger logger = LoggerFactory.getLogger(OpenMeteoService.class);

    private final OkHttpClient client;

    private String latitude;
    private String longitude;
    private String startDate;
    private String endDate;
    @Value("${weatherman.openmeteo.service.base_url}")
    private String baseUrl;

    public OpenMeteoService() {
        this.client = new OkHttpClient();
    }

    // Getting Data from OpenWeather API and convert it to DTO
    public OpenMeteoDTO.Root getWeather() {
        Request request = new Request.Builder()
                .url(baseUrl +
                        "?latitude=" + getLatitude() +
                        "&longitude=" + getLongitude() +
                        "&hourly=temperature_2m" +
                        //"&timezone=Europe%2FBerlin" +
                        "&start_date=" + getStartDate() +
                        "&end_date=" + getEndDate()
                        )
                .build();

        try {
            var response = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (response.code() == 200) {
                var openMeteoResponse = Objects.requireNonNull(response.body()).string();
                logger.debug("{}", openMeteoResponse);
                return mapper.readValue(openMeteoResponse, OpenMeteoDTO.Root.class);
            }
            return null;
        } catch (IOException | JSONException e) {
            logger.error("getWeather {}", e.getMessage());
        }
        return null;
    }

    // Getters and Setters for latitude, longitude, startDate and endDate
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

