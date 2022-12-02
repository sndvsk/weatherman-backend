package com.example.weatherman.repository;

import com.example.weatherman.model.back.OpenMeteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
public interface OpenMeteoRepository extends JpaRepository<OpenMeteo, Serializable> {

    OpenMeteo findByTime(Date time);

}
