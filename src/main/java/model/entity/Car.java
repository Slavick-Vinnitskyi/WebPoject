package model.entity;

import model.entity.enums.LicenseType;

import java.time.LocalDate;

public class Car {
    private int id;
    private String model;
    private LocalDate year;
    private LicenseType licenseType;


    public Car() {
    }

    public Car(String model, LocalDate year, LicenseType licenseType) {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
