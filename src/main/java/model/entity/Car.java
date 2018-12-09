package model.entity;

import model.entity.enums.LicenseType;

public class Car {
    private int id;
    private String model;
    private int year;

    public Car() {
    }

    public Car(String model, int year, LicenseType licenseType) {
        this.model = model;
        this.year = year;
        this.licenseType = licenseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private LicenseType licenseType;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
