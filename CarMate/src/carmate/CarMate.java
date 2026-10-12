package carmate;

import java.util.*;

public class CarMate {

    private static final String DATA_FOLDER = "data/";
    private static final String CAR_FILE = DATA_FOLDER + "cars.dat";
    private static final String ADMIN_FILE = DATA_FOLDER + "admins.dat";
    private static final String CUSTOMER_FILE = DATA_FOLDER + "customers.dat";
    private static final String EARNING_FILE = DATA_FOLDER + "earnings.dat";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDefaultAdmin();
        mainMenu();
    }

    
      private static void initializeDefaultAdmin() {
        List<Admin> admins = FileHandler.readFromFile(ADMIN_FILE);
        if (admins.isEmpty()) {
            Admin defaultAdmin = new Admin("ashiq", "1711");
            admins.add(defaultAdmin);
            FileHandler.writeToFile(ADMIN_FILE, admins);
            System.out.println("Default admin created: Username: ashiq, Password: 1711");
        }
    }
    private static void mainMenu() {
        while (true) {
            System.out.println("\n=== CarMate Main Menu ===");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 ->
                    adminMenu();
                case 2 ->
                    customerMenu();
                case 3 -> {
                    System.out.println("Exiting CarMate. Goodbye!");
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu() {

        if (!adminLogin()) {
            return;
        }

        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View Cars");
            System.out.println("2. Add New Car");
            System.out.println("3. Remove Car");
            System.out.println("4. See Customer Information");
            System.out.println("5. View Earnings");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 ->
                    viewCars();
                case 2 ->
                    addNewCar();
                case 3 ->
                    removeCar();
                case 4 ->
                    viewCustomerInformation();
                case 5 ->
                    viewEarnings();
                case 6 -> {
                    System.out.println("Exiting to Main Menu...");
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        List<Admin> admins = FileHandler.readFromFile(ADMIN_FILE);
        for (Iterator<Admin> it = admins.iterator(); it.hasNext();) {
            Admin admin = it.next();
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Admin login successful.");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    private static void viewCars() {
        List<Car> cars;
        cars = FileHandler.readFromFile(CAR_FILE);
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }
        System.out.println("\n=== Cars Available ===");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /*
private static void viewCars() {
    // Assuming 'cars' is a list of Car objects
            List<Car> cars ;
    System.out.println("------------------------------------------------------------------------------------");
    System.out.printf("%-15s%-15s%-15s%-20s%-15s\n", "Reg No", "Brand", "Model", "Price Per Day", "Location");
    System.out.println("--------------------------------------------------------------------------------------");
    
    // Iterate over the cars and print each one in the desired format
    for (Car car : Cars) {
        System.out.printf("%-15s%-15s%-15s%-20.2f%-15s\n", 
                          car.getRegistrationNumber(), 
                          car.getBrand(), 
                          car.getModel(), 
                          car.getPricePerDay(), 
                          car.getLocation());
        
    }
    System.out.println("------------------------------------------------------------");
}
*/

    private static void addNewCar() {
        System.out.print("Enter Registration Number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Price Per Day: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        Car newCar = new Car(regNo, brand, model, price, location);

        List<Car> cars = FileHandler.readFromFile(CAR_FILE);
        cars.add(newCar);
        FileHandler.writeToFile(CAR_FILE, cars);

        System.out.println("Car added successfully.");
    }

    private static void removeCar() {
        System.out.print("Enter Registration Number of Car to Remove: ");
        String regNo = scanner.nextLine();

        List<Car> cars = FileHandler.readFromFile(CAR_FILE);
        boolean found = cars.removeIf(car -> car.getRegistrationNumber().equals(regNo));

        if (found) {
            FileHandler.writeToFile(CAR_FILE, cars);
            System.out.println("Car with Registration Number " + regNo + " has been removed.");
        } else {
            System.out.println("No car found with the given registration number.");
        }
    }

    private static void viewCustomerInformation() {
        List<Customer> customers = FileHandler.readFromFile(CUSTOMER_FILE);
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\n=== Customer Information ===");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void viewEarnings() {
        List<Earning> earnings = FileHandler.readFromFile(EARNING_FILE);
        if (earnings.isEmpty()) {
            System.out.println("No earnings recorded.");
            return;
        }
        System.out.println("\n=== Earnings ===");
        for (Earning earning : earnings) {
            System.out.println(earning);
        }
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 ->
                    rentCar();
                case 2 ->
                    returnCar();
                case 3 -> {
                    System.out.println("Exiting to Main Menu...");
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^01\\d{9}$"); // Matches 01 followed by 9 digits
    }

    private static void rentCar() {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Collect Customer Details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Step 2: Validate Phone Number
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number. Returning to Customer Menu.");
            return; // Exit the method, returning to the Customer Menu
        }

        // Step 3: Collect Location
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();

        // Continue with the rest of the rental process (search cars by location, etc.)
        // This part remains unchanged.
        Customer customer = new Customer(name, phoneNumber, location);
        List<Customer> customers = FileHandler.readFromFile(CUSTOMER_FILE);
        customers.add(customer);
        FileHandler.writeToFile(CUSTOMER_FILE, customers);

        List<Car> cars = FileHandler.readFromFile(CAR_FILE);
        List<Car> availableCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.getLocation().equalsIgnoreCase(location) && car.isAvailable()) {
                availableCars.add(car);
            }
        }

        if (availableCars.isEmpty()) {
            System.out.println("No available cars at your location.");
            return;
        }

        System.out.println("\n=== Available Cars ===");
        for (Car car : availableCars) {
            System.out.println(car);
        }

        System.out.print("Enter Registration Number of the car you want to rent: ");
        String regNo = scanner.nextLine();

        Car selectedCar = null;
        for (Car car : availableCars) {
            if (car.getRegistrationNumber().equalsIgnoreCase(regNo)) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("No car found with the given registration number.");
            return;
        }

        System.out.print("Enter number of days you want to rent the car: ");
        int days = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        double totalCost = selectedCar.getPricePerDay() * days;
        System.out.println("Total Cost: " + totalCost);
        System.out.print("Do you want to proceed with the booking (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            // Update car availability
            selectedCar.setAvailable(false);

            // Update the car file
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)) {
                    cars.set(i, selectedCar);
                    break;
                }
            }
            FileHandler.writeToFile(CAR_FILE, cars);

            // Record the earnings
            Earning earning = new Earning(selectedCar.getRegistrationNumber(), totalCost);
            List<Earning> earnings = FileHandler.readFromFile(EARNING_FILE);
            earnings.add(earning);
            FileHandler.writeToFile(EARNING_FILE, earnings);

            System.out.println("Booking successful. Enjoy your ride!");
        } else {
            System.out.println("Booking canceled.");
        }
    }

    private static void returnCar() {
        System.out.print("Enter Registration Number of the car you want to return: ");
        String regNo = scanner.nextLine();

        List<Car> cars = FileHandler.readFromFile(CAR_FILE);
        Car carToReturn = null;

        for (Car car : cars) {
            if (car.getRegistrationNumber().equalsIgnoreCase(regNo) && !car.isAvailable()) {
                carToReturn = car;
                break;
            }
        }

        if (carToReturn == null) {
            System.out.println("No rented car found with the given registration number.");
            return;
        }

        // Update car availability
        carToReturn.setAvailable(true);

        // Update the car file
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)) {
                cars.set(i, carToReturn);
                break;
            }
        }
        FileHandler.writeToFile(CAR_FILE, cars);

        System.out.println("Car returned successfully. Thank you for using CarMate!");
    }

}
