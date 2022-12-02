package com.example.weatherman.model.back;

import com.example.weatherman.dataobject.IdDataObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("DefaultAnnotationParam")
@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "entity_openmeteo_seq")
@Table(name = "openmeteo")
public class OpenMeteo extends IdDataObject implements Serializable {

    private long id;

    @Column(name = "time")
    private Date time;

    @Column(name = "temperature")
    private double temperature;

    public OpenMeteo(long id, Date time, double temperature) {
        this.id = id;
        this.time = time;
        this.temperature = temperature;
    }

    public OpenMeteo() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
