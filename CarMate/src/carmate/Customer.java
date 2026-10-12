package carmate;

import java.io.Serializable;

public class Customer implements Serializable {

    private String name;
    private String phoneNumber;
    private String location;

    public Customer(String name, String phoneNumber, String location) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Customer [Name: " + name + ", Phone: " + phoneNumber + ", Location: " + location + "]";
    }
}
