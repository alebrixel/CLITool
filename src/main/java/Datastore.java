import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Datastore {
    private static final Map<String, String> datastore = new HashMap<>();

    /**
     * Sets the data in the datastore for a specific URL.
     * @param url The URL for which the data should be stored.
     * @param resultOrErrorMessage The data or error message to be stored.
     */
    public static void setDatastore(String url, String resultOrErrorMessage) {
        datastore.put(url, resultOrErrorMessage);
        // Save the entire datastore to datastore.txt after each update
        saveToDatastore();
    }

    /**
     * Saves the entire datastore to the file datastore.txt.
     */
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
        } catch (IOException e) {
            System.out.println("Error occurred while saving datastore to file: " + e.getMessage());
        }
    }

    /**
     * Gets the current date and time formatted as "yyyy/MM/dd HH:mm:ss".
     * @return The formatted current date and time.
     */
    private static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    /**
     * Gets the entire datastore map.
     * @return The map containing the datastore.
     */
    public static Map<String, String> getDatastore() {
        return datastore;
    }
}
