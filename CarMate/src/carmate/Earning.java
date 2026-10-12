package carmate;

import java.io.Serializable;

public class Earning implements Serializable {

    private String registrationNumber;
    private double amount;

    public Earning(String registrationNumber, double amount) {
        this.registrationNumber = registrationNumber;
        this.amount = amount;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Earning [Car Registration Number: " + registrationNumber + ", Amount: " + amount + "]";
    }
}
