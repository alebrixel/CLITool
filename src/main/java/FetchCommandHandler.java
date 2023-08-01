public class FetchCommandHandler {

    /**
     * Handles the fetch command based on the command-line arguments.
     * @param args The command-line arguments passed to the program.
     */
    public void handleFetchCommand(String[] args) {
        boolean outputMode = false;

        // Check if the output mode is enabled in the command-line arguments
        if (args.length >= 2 && args[1].equals("--output")) {
            outputMode = true;
        }

        WebsiteHandler websiteHandler = new WebsiteHandler();

        // Start fetching data from websites based on the output mode
        for (int i = outputMode ? 2 : 1; i < args.length; i++) {
            String url = args[i];
            if (!url.startsWith("https://")) {
                url = "https://" + url;
            }
            websiteHandler.fetchDataFromWebsite(url, outputMode);
        }
    }
}