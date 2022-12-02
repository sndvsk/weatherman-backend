package com.example.weatherman.service;

import com.example.weatherman.dto.YrnoDTO;
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
public class YrnoService {
    private static final Logger logger = LoggerFactory.getLogger(YrnoService.class);

    private final OkHttpClient client;

    private String latitude;
    private String longitude;
    @Value("${weatherman.yrno.service.base_url}")
    private String baseUrl;

    // without User-Agent there is:
    // 403 Forbidden User-Agent header okhttp/4.6.0 is not allowed, use a unique identifier
    // error.
    private String userAgent = "PostmanRuntime/7.30.0";

    public YrnoService() {
        this.client = new OkHttpClient();
    }

    // Getting Data from OpenWeather API nd convert it to DTO
    public YrnoDTO.Root getWeather() {
        Request request = new Request.Builder()
                .url(baseUrl +
                        "?lat=" + getLatitude() +
                        "&lon=" + getLongitude()
                )
                .addHeader("User-Agent", userAgent)
                .build();

        try {
            var response = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (response.code() == 200) {
                var yrnoResponse = Objects.requireNonNull(response.body()).string();
                logger.debug("{}", yrnoResponse);
                return mapper.readValue(yrnoResponse, YrnoDTO.Root.class);
            }
            return null;
        } catch (IOException | JSONException e) {
            logger.error("getWeather {}", e.getMessage());
        }
        return null;
    }

    // Getters and Setters for latitude and longitude
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

}


