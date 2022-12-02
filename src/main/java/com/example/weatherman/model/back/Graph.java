package com.example.weatherman.model.back;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "graph")
public class Graph implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "time")
    @JsonProperty("time")
    private Date time;

    @Column(name = "yrno_temperature")
    @JsonProperty("yrno_temperature")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double yrnoTemperature;

    @Column(name = "weatherapi_temperature")
    @JsonProperty("weatherapi_temperature")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double weatherApiTemperature;

    @Column(name = "openmeteo_temperature")
    @JsonProperty("openmeteo_temperature")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double openmeteoTemperature;

    public Graph(long id, Date time, Double yrnoTemperature, Double weatherApiTemperature, Double openmeteoTemperature) {
        this.id = id;
        this.time = time;
        this.yrnoTemperature = yrnoTemperature;
        this.weatherApiTemperature = weatherApiTemperature;
        this.openmeteoTemperature = openmeteoTemperature;
    }

    public Graph() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getYrnoTemperature() {
        return yrnoTemperature;
    }

    public void setYrnoTemperature(Double yrnoTemperature) {
        this.yrnoTemperature = yrnoTemperature;
    }

    public Double getWeatherApiTemperature() {
        return weatherApiTemperature;
    }

    public void setWeatherApiTemperature(Double weatherApiTemperature) {
        this.weatherApiTemperature = weatherApiTemperature;
    }

    public Double getOpenmeteoTemperature() {
        return openmeteoTemperature;
    }

    public void setOpenmeteoTemperature(Double openmeteoTemperature) {
        this.openmeteoTemperature = openmeteoTemperature;
    }
}
