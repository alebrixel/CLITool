import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HistoryCommandHandler {
    public void handleHistoryCommand() {
        try (BufferedReader reader = new BufferedReader(new FileReader("datastore.txt"))) {
            System.out.println("Date and Time       - URL                   - Result or Error Message");
            System.out.println("--------------------------------------------------------------");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading datastore.txt: " + e.getMessage());
        }
    }
}
