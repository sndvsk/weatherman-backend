package com.example.weatherman.dataobject;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class IdDataObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "id")
    private long id;
}
