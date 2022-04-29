import java.util.*;
//import org.json.JSONArray;
import org.json.JSONObject;

public class Movie {

    private String id; // unique movie id from IMDB API
    private double finalRating; // user's final rating 
    public Map<String,Double> categoryRatings; // stores which categories were used to make the final rating

    // default constructor
    public Movie() {
        this.id = "";
        this.finalRating = -1;
        this.categoryRatings = new HashMap<>();
    }

    // main constructor
    public Movie(String inputId) {
        this.id = inputId;
        this.finalRating = -1;
        this.categoryRatings = new HashMap<>();
    }

    // Add to the movie object's hashmap of categories, input a category and the cooresponding rating
    public void addRating(String categoryName, Double inputRating) {
        this.categoryRatings.put(categoryName, inputRating);
    }

    // Set the movie object's overall rating
    public void setFinalRating(double inputRating) {
        this.finalRating = inputRating;
    }
    // get the movie objects overall rating
    public double getFinalRating() {
        return this.finalRating;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        APIHandler APIHandlerInstance = new APIHandler();
        String title = parse(APIHandlerInstance.searchMovieById(this.id));

        result.append(title + "\n" + "Rating: " + this.finalRating);
        return result.toString();
    }

    // API call returns a JSON object of the movie's data, parse through that and return what toString needs
    public static String parse (JSONObject responseBody) {
        JSONObject searchResults = responseBody;
        String fullTitle = searchResults.getString("fullTitle");

        return fullTitle;
    }

}