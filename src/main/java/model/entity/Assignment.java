package model.entity;

import java.time.LocalDate;
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

//    @Override
//    public String toString() {
//        return "Assignment{" +
//                "id=" + id +
//                ", date=" + date +
//                ", route=" + route +
//                ", status=" + status +
//                ", driver=" + driver +
//                ", bus=" + bus +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        return date.equals(that.date) &&
                route.equals(that.route) &&
                status == that.status;
    }
//
//    @Override
//    public int hashCode() {
//        int result = date.hashCode();
//        result = 31 * result + (route != null ? route.hashCode() : 0);
//        result = 31 * result + status.hashCode();
//        result = 31 * result + (driver != null ? driver.hashCode() : 0);
//        result = 31 * result + (bus != null ? bus.hashCode() : 0);
//        return result;
//    }
}
class A{
    public static void main(String[] args) {
        System.out.println(new Assignment().hashCode());
        System.out.println(new Assignment());
        System.out.println(Integer.toHexString(1711574013));
    }
}