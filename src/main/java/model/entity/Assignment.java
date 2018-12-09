package model.entity;

import java.time.LocalDate;

public class Assignment {

    public enum Status {
        assigned, applyied
    }

    private int id;
    private LocalDate date;
    private Route route;
    private User driver;
    private Car bus;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Car getBus() {
        return bus;
    }

    public void setBus(Car bus) {
        this.bus = bus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Assignment(LocalDate date, Route route) {
        this.date = date;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public Assignment() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
