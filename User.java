import java.util.*;
// implements Comparable<User>

public class User {
	private int id;
    private String username;
    private String password;
    private boolean pro;
    private int level;
    private int moviesRatedCount;
    public HashMap <String, Double> actionMultipliers;
    public HashMap <String, Double> comedyMultipliers;
    public HashMap <String, Double> dramaMultipliers;
    public HashMap<String, Movie> ratedMovies;

    // default constructor
    public User() {
        this.id = -1;
        this.username = "";
        this.password = "";
        this.level = 0;
        this.pro = false;
        this.actionMultipliers = new HashMap<>();
        this.comedyMultipliers = new HashMap<>();
        this.dramaMultipliers = new HashMap<>();
        this.ratedMovies = new HashMap<>();
    }

    // main constructor
    public User(String inputUsername, int inputId) {
        this.id = inputId;
        this.username = inputUsername;
        this.password = "";
        this.level = 0;
        this.pro = false;
        this.actionMultipliers = new HashMap<>();
        this.comedyMultipliers = new HashMap<>();
        this.dramaMultipliers = new HashMap<>();
        this.ratedMovies = new HashMap<>();
    }

    public User(int inputId, String inputUsername, String inputPassword) {
        this.id = inputId;
        this.username = inputUsername;
        this.password = inputPassword;
        this.pro = false;
        this.actionMultipliers = new HashMap<>();
        this.comedyMultipliers = new HashMap<>();
        this.dramaMultipliers = new HashMap<>();
        this.ratedMovies = new HashMap<>();
    }

    // Add a movie to the map of movies that the user has rated
    public void addMovie(String inputName, Movie inputMovie) {
        this.ratedMovies.put(inputName, inputMovie);
    }

    // Change account to pro
    public void setPro() {
        this.pro = true;
    }

    public boolean isPro() {
        return this.pro;
    }

    // Show profile stats and movies rated
    public String displayProfile() {
        StringBuilder result = new StringBuilder();

        // DISPLAY PROFILE
        result.append("Username: " + this.username + "\n");
        result.append("Profile Level: " + this.level + "\n");

        if (this.pro == true) {
            result.append("Account Type: Pro" + "\n");
        } else {
            result.append("Account Type: Standard" + "\n");
        }

        result.append("Number of Movies Rated: " + this.moviesRatedCount + "\n");
        result.append("\n");


        // DISPLAY MOVIES
        for (Map.Entry<String, Movie> entry : this.ratedMovies.entrySet()) {
            Movie value = entry.getValue(); // movie obj
            result.append(value.toString());
        }
        if (result.length() == 0) {
            System.out.println("You have no rated movies, rate a movie to add it do your profile.");
        }

        return result.toString();
    }

    public void increaseMovieCount() {
        this.moviesRatedCount++;
    }

}