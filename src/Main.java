import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsageInstructions();
            return;
        }

        // Check the command-line arguments to determine the action to perform
        if (args[0].equals("--live")) {
            LiveQueryHandler liveQueryHandler = new LiveQueryHandler();
            liveQueryHandler.handleLiveQuery(args);
        } else if (args[0].equals("--fetch")) {
            FetchCommandHandler fetchCommandHandler = new FetchCommandHandler();
            fetchCommandHandler.handleFetchCommand(args);
        } else if (args[0].equals("--history")) {
            handleHistoryCommand();
        } else if (args[0].equals("--backup")) {
            createBackup();
        } else {
            WebsiteHandler websiteHandler = new WebsiteHandler();

            // Perform website availability check for each URL passed as an argument
            for (String url : args) {
                websiteHandler.checkWebsiteAvailability(url);
            }
        }
    }


    /**
     * Prints the usage instructions for the command-line application.
     */
    public static void printUsageInstructions() {
        System.out.println("Usage: java Main <command> [options] [URLs]");
        System.out.println("Commands:");
        System.out.println("  --fetch [URLs]               Fetch data from the specified URLs");
        System.out.println("  --fetch --output [URLs]      Fetch data from the URLs and show output");
        System.out.println("  --live [URLs]                Perform live query with default interval (5 seconds)");
        System.out.println("  --live -<seconds> [URLs]     Perform live query with custom interval");
        System.out.println("  --live --output [URLs]       Perform live query with output and default interval");
        System.out.println("  --live --output -<seconds> [URLs] Perform live query with output and custom interval");
        System.out.println("  --history                    Show history of fetched data");
    }

    /**
     * Handles the history command to show the history of fetched data.
     */
    public static void handleHistoryCommand() {
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

    /**
     * Creates a backup of data in the current data store and saves it into a CSV file.
     */
    public static void createBackup() {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String backupFileName = "datastore_backup_" + currentDateTime.format(formatter) + ".csv";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFileName))) {
                for (Map.Entry<String, String> entry : Datastore.getDatastore().entrySet()) {
                    String url = entry.getKey();
                    String resultOrErrorMessage = entry.getValue();
                    String line = url + "," + resultOrErrorMessage;
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Datastore backup created and saved to " + backupFileName);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating the datastore backup: " + e.getMessage());
        }
    }
}
