package com.example.weatherman.repository;

import com.example.weatherman.model.back.Yrno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
public interface YrnoRepository extends JpaRepository<Yrno, Serializable> {

    Yrno findByTime(Date time);

}
