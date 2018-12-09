package model.entity;
//TODO : подумать добавить ли билдер для маршрута
public class Route {
    private int id;
    private String start;
    private String finish;
    private String startUa;
    private String finishUa;

    public Route() {
    }

    public Route(int id, String start, String finish, String startUa, String finishUa) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.startUa = startUa;
        this.finishUa = finishUa;
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

    public String getStartUa() {
        return startUa;
    }

    public void setStartUa(String startUa) {
        this.startUa = startUa;
    }

    public String getFinishUa() {
        return finishUa;
    }

    public void setFinishUa(String finishUa) {
        this.finishUa = finishUa;
    }
}
