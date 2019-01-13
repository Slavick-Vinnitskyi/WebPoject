package model.entity;

import java.util.Objects;

public class Route {
    private int id;
    private String start;
    private String finish;

    public Route(int id, String start, String finish) {
        this.id = id;
        this.start = start;
        this.finish = finish;
    }

    public Route(String start, String finish) {
        this.start = start;
        this.finish = finish;
    }

    public Route() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return start.equals(route.start)
                && finish.equals(route.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
