package carmate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static <T> List<T> readFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + filePath + " - " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static <T> void writeToFile(String filePath, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath + " - " + e.getMessage());
        }
    }
}
