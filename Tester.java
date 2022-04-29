// package com.company;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
// import org.json.*;

//import org.json.JSONArray;

public class Tester {

//private static HttpURLConnection connection;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //JSONArray test1 = new JSONArray();
        //LRUCache cache = new LRUCache(3);

        String userInput = "";

        // CACHE TESTER SUCCESSFUL
        // while (userInput.compareTo("x") != 0) {
        //     userInput = scan.nextLine();

        //     if (cache.check(userInput) == false) {
        //         System.out.println("Cache Miss");
        //         cache.update(userInput, test1);  

        //     }   else {
        //         System.out.println("Cache Hit");
        //         test1 = cache.get(userInput);
        //     }
    
        //     cache.showContents();
        // }

        APIHandler handle = new APIHandler();

        while (userInput.compareTo("x") != 0) {
            userInput = scan.nextLine();

            JSONArray temp = handle.searchMovieByTitle(userInput);
            ArrayList<String> movieIDs = parse(temp);
        }
    }

    public static ArrayList<String> parse (JSONArray responseBody) {
        JSONArray movies = responseBody;
        ArrayList<String> movieIDs = new ArrayList<>();
        System.out.println("Please select from the following options:");
    
        for (int i = 0; i < movies.length(); i++) {
            JSONObject movie = movies.getJSONObject(i);
            String id = movie.getString("id");
            String title = movie.getString("title");
            String year = movie.getString("description");

            movieIDs.add(id);
            System.out.print((i + 1) + ". ");
            System.out.println(title + " " + year);
        }

        return movieIDs;
    }
}