import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Datastore {
    private static final Map<String, String> datastore = new HashMap<>();

    public static void setDatastore(String url, String resultOrErrorMessage) {
        datastore.put(url, resultOrErrorMessage);
        saveToDatastore();
    }

    public static void saveToDatastore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("datastore.txt", true))) {
            for (String url : datastore.keySet()) {
                String resultOrErrorMessage = datastore.get(url);
                String currentDateTime = getCurrentDateTime();
                String line = currentDateTime + " - " + url + " - " + resultOrErrorMessage;
                writer.write(line);
                writer.newLine(); // Write a new line separator
            }
            writer.flush(); // Flush the buffer to ensure data is immediately written to the file
            System.out.println("Datastore saved to datastore.txt");
        } catch (IOException e) {
            System.out.println("Error occurred while saving datastore to file: " + e.getMessage());
        }
    }

    private static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
}
