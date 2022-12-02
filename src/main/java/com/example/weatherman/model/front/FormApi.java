package com.example.weatherman.model.front;

import com.fasterxml.jackson.annotation.JsonProperty;


@SuppressWarnings("ClassCanBeRecord")
public class FormApi {
    @JsonProperty("apiForm")
    private final ApiForm apiForm;

    @JsonProperty("coordsForm")
    private final CoordsForm coordsForm;

    @JsonProperty("dateForm")
    private final DateForm dateForm;

    public FormApi(ApiForm apiForm, CoordsForm coordsForm, DateForm dateForm) {
        this.apiForm = apiForm;
        this.coordsForm = coordsForm;
        this.dateForm = dateForm;
    }

    public ApiForm getApiForm() {
        return apiForm;
    }

    public CoordsForm getCoordsForm() {
        return coordsForm;
    }

    public DateForm getDateForm() {
        return dateForm;
    }
}
