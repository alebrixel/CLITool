import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class WebsiteHandler {
    private Map<String, String> datastore;

    public WebsiteHandler() {
        datastore = new HashMap<>();
    }
    /**
     * Fetches data from a website and handles the response.
     * @param url The URL of the website to fetch data from.
     * @param liveOutputMode A boolean indicating whether to print output in live query mode.
     */
    public void fetchDataFromWebsite(String url, boolean liveOutputMode) {
        try {
            URL websiteUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) websiteUrl.openConnection();
            connection.setRequestMethod("HEAD");

            int responseCode = connection.getResponseCode();
            String result = "Response Code: " + responseCode;

            // If in output mode, print the fetched data
            if (liveOutputMode) {
                System.out.println(url + " fetched. Result: " + result);
            }

            // Save the data to datastore.txt after each query
            Datastore.setDatastore(url, result);
        } catch (java.net.UnknownHostException e) {
            String errorMessage = "Error: The host " + url + " could not be resolved.";
            if (liveOutputMode) {
                System.out.println(errorMessage);
            }

            // Save the data to datastore.txt after each query
            Datastore.setDatastore(url, errorMessage);
        } catch (java.net.ConnectException e) {
            String errorMessage = "Error: Connection refused for " + url + ".";
            if (liveOutputMode) {
                System.out.println(errorMessage);
            }

            // Save the data to datastore.txt after each query
            Datastore.setDatastore(url, errorMessage);
        } catch (IOException e) {
            String errorMessage = "Error occurred while fetching " + url + ": " + e.getMessage();
            if (liveOutputMode) {
                System.out.println(errorMessage);
            }

            // Save the data to datastore.txt after each query
            Datastore.setDatastore(url, errorMessage);
        }
    }
    /**
     * Checks the availability of a website by sending a HEAD request.
     * @param url The URL of the website to check.
     */
    public void checkWebsiteAvailability(String url) {
        try {
            URL websiteUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) websiteUrl.openConnection();
            connection.setRequestMethod("HEAD");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println(url + " is available.");
            } else {
                System.out.println(url + " is not available. Response Code: " + responseCode);
            }
        } catch (java.net.UnknownHostException e) {
            System.out.println("Error: The host " + url + " could not be resolved.");
        } catch (java.net.ConnectException e) {
            System.out.println("Error: Connection refused for " + url + ".");
        } catch (IOException e) {
            System.out.println("Error occurred while checking " + url + " availability: " + e.getMessage());
        }
    }

}


