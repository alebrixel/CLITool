public class FetchCommandHandler {

    public void handleFetchCommand(String[] args) {
        boolean outputMode = false;

        if (args.length >= 2 && args[1].equals("--output")) {
            outputMode = true;
        }

        WebsiteFetcher websiteFetcher = new WebsiteFetcher();

        for (int i = outputMode ? 2 : 1; i < args.length; i++) {
            websiteFetcher.fetchDataFromWebsite(args[i], outputMode);
        }
    }
}
