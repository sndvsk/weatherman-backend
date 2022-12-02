package com.example.weatherman.model.back;

import com.example.weatherman.dataobject.IdDataObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "entity_form_seq")
@Table(name = "formstorage")
public class FormStorage extends IdDataObject implements Serializable {

    private long id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "api_names")
    private String apiNames;

    public FormStorage(long id, double latitude, double longitude, Date startDate, Date endDate, String apiNames) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.apiNames = apiNames;
    }

    public FormStorage() {

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApiNames() {
        return apiNames;
    }

    public void setApiNames(String apiNames) {
        this.apiNames = apiNames;
    }
}
