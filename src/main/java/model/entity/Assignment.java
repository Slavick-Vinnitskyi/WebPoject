package model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {

    public enum Status {
        assigned, applied
    }

    private int id;
    private LocalDate date;
    private Route route;
    private Status status;
    private User driver;
    private Car bus;

    public Assignment(LocalDate date, Route route) {
        this.date = date;
        this.route = route;
    }

    public Assignment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        if (!date.equals(that.date)) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (status != that.status) return false;
        if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;
        return bus != null ? bus.equals(that.bus) : that.bus == null;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + status.hashCode();
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (bus != null ? bus.hashCode() : 0);
        return result;
    }
}