import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LiveQueryHandler {

    // Default interval in seconds for the live query
    private int interval = 5;

    /**
     * Handles the live query based on the command-line arguments.
     * @param args The command-line arguments passed to the program.
     */
    public void handleLiveQuery(String[] args) {
        boolean outputMode = false;
        boolean customInterval = false;

        WebsiteHandler websiteHandler = new WebsiteHandler();
        int startIndex = 1;

        // Check command-line arguments for output mode and custom interval
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args[i].equals("--output")) {
                    outputMode = true;
                } else if (args[i].matches("-\\d+")) {
                    customInterval = true;
                    interval = Integer.parseInt(args[i].substring(1)); // Extract the number from the argument
                }
            } else {
                startIndex = i;
                break;
            }
        }

        // Perform the initial check for each URL
        for (int i = startIndex; i < args.length; i++) {
            websiteHandler.fetchDataFromWebsite(args[i], outputMode);
        }

        // If custom interval is specified, run the live query continuously
        if (customInterval) {
            runLiveQueryWithCustomInterval(args, websiteHandler);
        } else {
            runLiveQueryWithDefaultInterval(args, websiteHandler);
        }
    }

    /**
     * Runs the live query with a custom interval based on the provided command-line argument.
     * @param args The command-line arguments passed to the program.
     * @param websiteHandler The instance of the WebsiteHandler class to handle fetching data from websites.
     */
    private void runLiveQueryWithCustomInterval(String[] args, WebsiteHandler websiteHandler) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            for (int i = 1; i < args.length; i++) {
                websiteHandler.fetchDataFromWebsite(args[i], true);
                Datastore.saveToDatastore(); // Save the data to datastore.txt after each query
              }
        };
        scheduler.scheduleAtFixedRate(task, interval, interval, TimeUnit.SECONDS);
    }

    /**
     * Runs the live query with the default interval (5 seconds).
     * @param args The command-line arguments passed to the program.
     * @param websiteHandler The instance of the WebsiteHandler class to handle fetching data from websites.
     */
    private void runLiveQueryWithDefaultInterval(String[] args, WebsiteHandler websiteHandler) {
        while (true) {
            try {
                // Wait for the default interval (5 seconds) before the next query
                Thread.sleep(interval * 1000); // Convert interval from seconds to milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Perform the live query for each URL
            for (int i = 1; i < args.length; i++) {
                websiteHandler.fetchDataFromWebsite(args[i], true);
                Datastore.saveToDatastore(); // Save the data to datastore.txt after each query
            }
        }
    }
}
