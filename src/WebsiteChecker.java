import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteChecker {
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
