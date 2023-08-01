import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteFetcher {
    public void fetchDataFromWebsite(String url, boolean liveOutputMode) {
        try {
            URL websiteUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) websiteUrl.openConnection();
            connection.setRequestMethod("HEAD");

            int responseCode = connection.getResponseCode();
            String result = "Response Code: " + responseCode;

            Datastore.setDatastore(url, result);

            if (liveOutputMode) {
                System.out.println(url + " fetched. Result: " + result);
            }
        } catch (IOException e) {
            String errorMessage = "Error occurred while fetching " + url + ": " + e.getMessage();
            Datastore.setDatastore(url, errorMessage);

            if (liveOutputMode) {
                System.out.println(errorMessage);
            }
        }
    }
}
