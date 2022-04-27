package com.rentalkars.hibernate.entity;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "num_plate"}))
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "num_plate", nullable = false)
    private String numPlate;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rent> reservation;

    public Car(){

    }

    public Car(String manufacturer, String model, String type, String numPlate, LocalDate regDate) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.numPlate = numPlate;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumPlate() {
        return numPlate;
    }

    public void setNumPlate(String numPlate) {
        this.numPlate = numPlate;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}
