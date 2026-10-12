package carmate;

import java.io.Serializable;

public class Car implements Serializable {

    private String registrationNumber;
    private String brand;
    private String model;
    private double pricePerDay;
    private String location;
    private boolean isAvailable;

    public Car(String registrationNumber, String brand, String model, double pricePerDay, String location) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.location = location;
        this.isAvailable = true; // Initially, the car is available
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
/*
    @Override
    public String toString() {
        return "Car[Reg No: " + registrationNumber + ", Brand: " + brand + ",   Model: " + model
                + ",Price Per Day: " + pricePerDay + ",Location: " + location + ",Availability: "
                + (isAvailable ? "Available" : "Not Available") + "]";
    }*/
}
