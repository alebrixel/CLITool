public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsageInstructions();
            return;
        }

        if (args[0].equals("--live")) {
            LiveQueryHandler liveQueryHandler = new LiveQueryHandler();
            liveQueryHandler.handleLiveQuery(args);
        } else if (args[0].equals("--fetch")) {
            FetchCommandHandler fetchCommandHandler = new FetchCommandHandler();
            fetchCommandHandler.handleFetchCommand(args);
        } else if (args[0].equals("--history")) {
            HistoryCommandHandler historyCommandHandler = new HistoryCommandHandler();
            historyCommandHandler.handleHistoryCommand();
        } else {
            WebsiteChecker websiteChecker = new WebsiteChecker();
            for (String url : args) {
                websiteChecker.checkWebsiteAvailability(url);
            }
        }
    }
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
}
