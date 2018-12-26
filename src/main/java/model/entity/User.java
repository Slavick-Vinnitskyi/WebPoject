package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    public enum ROLE {
        driver, admin, guest
    }

    private int id;
    private String login;
    private String password;
    private ROLE role;

    private String firstName;
    private String secondName;
    private String firstName_ua;
    private String secondName_ua;

    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public ForeignName getNameBundle(String firstName, String secondName) {
        return new ForeignName(firstName, secondName);
    }

    public class ForeignName {
        private String firstName;

        private String secondName;

        public ForeignName(String firstName, String secondName) {
            this.firstName = firstName;
            this.secondName = secondName;
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

    }

    public ROLE getRole() {
        if (role == null) {
            return ROLE.guest;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(firstName_ua, user.firstName_ua) &&
                Objects.equals(secondName_ua, user.secondName_ua) &&
                Objects.equals(cars, user.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
}