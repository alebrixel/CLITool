import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LiveQueryHandler {
    private int interval = 5; // Default interval in seconds

    public void handleLiveQuery(String[] args) {
        boolean outputMode = false;
        boolean customInterval = false;

        WebsiteFetcher websiteFetcher = new WebsiteFetcher();
        int startIndex = 1;

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

        // Perform the initial check
        for (int i = startIndex; i < args.length; i++) {
            websiteFetcher.fetchDataFromWebsite(args[i], outputMode);
        }

        // If custom interval is specified, run the live query continuously
        if (customInterval) {
            runLiveQueryWithCustomInterval(args, websiteFetcher);
        } else {
            runLiveQueryWithDefaultInterval(args, websiteFetcher);
        }
    }

    private void runLiveQueryWithCustomInterval(String[] args, WebsiteFetcher websiteFetcher) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            for (int i = 1; i < args.length; i++) {
                websiteFetcher.fetchDataFromWebsite(args[i], true);
                Datastore.saveToDatastore(); // Save the data to datastore.txt after each query
            }
        };
        scheduler.scheduleAtFixedRate(task, interval, interval, TimeUnit.SECONDS);
    }

    private void runLiveQueryWithDefaultInterval(String[] args, WebsiteFetcher websiteFetcher) {
        while (true) {
            try {
                // Wait for the default interval (5 seconds) before the next query
                Thread.sleep(interval * 1000); // Convert interval from seconds to milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Perform the live query for each URL
            for (int i = 1; i < args.length; i++) {
                websiteFetcher.fetchDataFromWebsite(args[i], true);
                Datastore.saveToDatastore(); // Save the data to datastore.txt after each query
            }
        }
    }
}
