package model.dto;

import model.entity.Assignment;
import model.entity.Route;

import java.time.LocalDate;

public class IndexDto {

    private int assignmentId;
    private LocalDate date;
    private Route route;
    private Assignment.Status status;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
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

    public Assignment.Status getStatus() {
        return status;
    }

    public void setStatus(Assignment.Status status) {
        this.status = status;
    }
}
