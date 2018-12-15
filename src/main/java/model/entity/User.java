package model.entity;

public class User {
    //TODO : спросить как разделить админа и водителя чтобы правильно параметризоавть interface
    private int id;
    private String login;
    private String password;
    private ROLE role;

    private String firstName;

    private String secondName;
    private String firstName_ua;
    private String secondName_ua;

    public enum ROLE {
        driver, admin, guest
    }
    public ROLE getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName_ua() {
        return firstName_ua;
    }

    public void setFirstName_ua(String firstName_ua) {
        this.firstName_ua = firstName_ua;
    }

    public String getSecondName_ua() {
        return secondName_ua;
    }
    public void setSecondName_ua(String secondName_ua) {
        this.secondName_ua = secondName_ua;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
