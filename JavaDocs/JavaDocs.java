import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;      // The "browser"
import java.net.http.HttpRequest;     // The request for the URL
import java.net.http.HttpResponse;    // The response from the server

public class JavaDocs {

    public static void main(String[] args) {
        
        // Let's use the official Java 17 Javadoc for java.util.Collections
        String url = "https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html";

        // 1. Create a new HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // 2. Build a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            System.out.println("Fetching HTML from: " + url);
            
            // 3. Send the request and get a response as a String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Get the raw HTML body from the response
            String htmlBody = response.body();

            // 5. Print the entire HTML (this will be a lot of text!)
            System.out.println("\n--- START OF HTML ---");
            System.out.println(htmlBody);
            System.out.println("--- END OF HTML ---");

        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred while fetching the page:");
            e.printStackTrace();
        }
    }
}
