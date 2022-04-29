//import java.util.*;
import java.net.http.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

// handles all API connections and cashed data

public class APIHandler {
    private String IMDB_TITLE_SEARCH; // stores link used to search IMDB API by title input
    private String IMDB_ID_SEARCH; // stores link used to search IMDB API by id input
    private LRUCache cache;
    private int LIMIT = 10; // cache

    public APIHandler() {
        this.IMDB_TITLE_SEARCH = "https://imdb-api.com/en/API/SearchMovie/k_28nyce3o/"; // IMDB search by title API link
        this.IMDB_ID_SEARCH = "https://imdb-api.com/en/API/Title/k_28nyce3o/"; // IMDB search by id API link
        this.cache = new LRUCache(LIMIT);
    }

    /* 
    * SEARCH IMDB API FOR MOVIES BY TITLE
    * parameters: String movie title input from user 
    * return: JSON Array of JSON Objects, each being a movie object
    */
    public JSONArray searchMovieByTitle (String inputTitle) {

        if (this.cache.check(inputTitle) == false) {

            this.cache.miss();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(IMDB_TITLE_SEARCH + inputTitle)).build();

            JSONArray results = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(APIHandler::parseMovieTitle)
                    .join();

            this.cache.update(inputTitle, results);  
        } else {
            this.cache.hit();
        }

        return this.cache.get(inputTitle);
    }

    // Parse data recieved when search for movie by title
    public static JSONArray parseMovieTitle (String responseBody) {
        JSONObject searchResults = new JSONObject(responseBody);
        JSONArray movies = searchResults.getJSONArray("results");
        return movies;
    }

    /* 
    * SEARCH IMDB API FOR MOVIES BY ID (all valid IMDB ids start with tt)
    * parameters: String unique movie id
    * return: JSON Object of movie with that id
    */
    public JSONObject searchMovieById (String inputId) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(IMDB_ID_SEARCH + inputId)).build();

        JSONObject result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIHandler::parseMovieId)
                .join();

        return result;
    }

    // Parse data recieved when search for movie by id
    public static JSONObject parseMovieId (String responseBody) {
        JSONObject result = new JSONObject(responseBody);
        return result;
    }

    // can be used later to see api lookup and cache stats
    public double showStats() {
        return this.cache.calcEfficacy();
    }
}