package models;

import java.math.BigDecimal;

public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private BigDecimal price;
    private String color;
    private int mileage;
    private String status;
    private String additionalInfo;

    public Car(int id, String make, String model, int year, BigDecimal price, String color, int mileage, String status, String additionalInfo) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
        this.additionalInfo = additionalInfo;
    }

    public Car(String make, String model, int year, BigDecimal price, String color, int mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
        this.status = "available";
        this.additionalInfo = "";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}