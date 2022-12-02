package com.example.weatherman.model.front;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ApiForm {

    @JsonProperty("yrnoApi")
    public boolean yrnoApi;

    @JsonProperty("openmeteoApi")
    public boolean openmeteoApi;

    @JsonProperty("weatherApi")
    public boolean weatherApi;

    public ApiForm(boolean yrnoApi, boolean openmeteoApi, boolean weatherApi) {
        this.yrnoApi = yrnoApi;
        this.openmeteoApi = openmeteoApi;
        this.weatherApi = weatherApi;
    }

    public boolean isYrnoApi() {
        return yrnoApi;
    }

    public void setYrnoApi(boolean yrnoApi) {
        this.yrnoApi = yrnoApi;
    }

    public boolean isOpenmeteoApi() {
        return openmeteoApi;
    }

    public void setOpenmeteoApi(boolean openmeteoApi) {
        this.openmeteoApi = openmeteoApi;
    }

    public boolean isWeatherApi() {
        return weatherApi;
    }

    public void setWeatherApi(boolean weatherApi) {
        this.weatherApi = weatherApi;
    }

    public String getApiList () {

        List<String> apiList = new ArrayList<>();

        if (isYrnoApi()) {
            apiList.add("yrnoApi");
        }
        if (isOpenmeteoApi()) {
            apiList.add("openmeteoApi");
        }
        if (isWeatherApi()) {
            apiList.add("weatherApi");
        }

        return String.join(", ", apiList);
    }

}
