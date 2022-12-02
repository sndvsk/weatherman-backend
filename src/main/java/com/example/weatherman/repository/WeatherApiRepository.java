package com.example.weatherman.repository;

import com.example.weatherman.model.back.WeatherApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
public interface WeatherApiRepository extends JpaRepository<WeatherApi, Serializable> {

    WeatherApi findByTime(Date time);

}
