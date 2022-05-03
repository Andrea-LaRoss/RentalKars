package com.rentalkars.hibernate.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "rent")
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private String status;

    public Rent(){

    }

    public Rent(LocalDate startDate, LocalDate endDate, Car car, User user, String status){
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        this.user = user;
        this.status = status;
    }


    public Rent(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

}
