package com.example.myrest.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

//    todo generate own id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

//    todo version

//    todo equals etc
}
