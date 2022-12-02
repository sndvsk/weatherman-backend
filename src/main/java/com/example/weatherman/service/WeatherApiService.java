package com.example.weatherman.service;

import com.example.weatherman.dto.WeatherApiDTO;
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
public class WeatherApiService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherApiService.class);

    private final OkHttpClient client;

    private String latitude;
    private String longitude;
    private String dayCount;
    @Value("${weatherman.weatherapi.service.secret_key}")
    private String apiKey;
    @Value("${weatherman.weatherapi.service.base_url}")
    private String baseUrl;

    public WeatherApiService() {
        this.client = new OkHttpClient();
    }

    // Getting Data from OpenWeather API and convert it to DTO
    public WeatherApiDTO.Root getWeather() {
        if (apiKey.compareToIgnoreCase("NO_SECRET_KEY") == 0) {
            logger.warn("weatherman.weatherapi.service.secret_key is not defined");
        }

        Request request = new Request.Builder()
                .url(baseUrl +
                        "?key=" + apiKey +
                        "&q=" + getLatitude() + "," + getLongitude() +
                        "&days=" + getDayCount() +
                        "&aqi=no&alerts=no")
                .build();

        try {
            var response = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (response.code() == 200) {
                var weatherApiResponse = Objects.requireNonNull(response.body()).string();
                logger.debug("{}", weatherApiResponse);
                return mapper.readValue(weatherApiResponse, WeatherApiDTO.Root.class);
            }
            return null;
        } catch (IOException | JSONException e) {
            logger.error("getWeather {}", e.getMessage());
        }
        return null;
    }

    // Getters and Setters for latitude, longitude and dayCount
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

    public String getDayCount() {
        return dayCount;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }
}
