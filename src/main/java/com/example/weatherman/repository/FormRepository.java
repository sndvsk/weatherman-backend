package com.example.weatherman.repository;

import com.example.weatherman.model.back.FormStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface FormRepository extends JpaRepository<FormStorage, Serializable> {

}
