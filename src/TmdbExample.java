/* For testing the API, not in use */
package src;

// Import the necessary Java libraries
import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class TmdbExample {

    // Replace with your own TMDb API key
    private static final String API_KEY = "YOUR_API_KEY";

    public static void main(String[] args) {
        try {
            // Set the URL for the TMDb API endpoint
            URL tmdbApiUrl = new URL(
                    "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=Fate+of+Furious");

            // Open a connection to the TMDb API endpoint
            HttpURLConnection connection = (HttpURLConnection) tmdbApiUrl.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Set the request headers
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            // Send the GET request to the TMDb API endpoint
            connection.connect();

            // Read the response from the TMDb API endpoint
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // Parse the response as a JSON object
            JSONObject json = new JSONObject(response.toString());
            System.out.println(json.getJSONArray("results").getJSONObject(0).getString("genre"));

            // Print the movie title
            System.out.println(json.getString("title"));
        } catch (IOException e) {
            System.err.println("An error occurred while fetching the movie details: " + e.getMessage());
        }
    }
}
