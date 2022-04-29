import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/*
change ratings of previously rated movies
delete ratings

change cache to work on search by id as well, show profile can use cache then
wrap java object in array
get parse movie id to take an array and then take object out of array
parse data in api handler???


add levels to profile using points based system based on - 
how many movies reviewed
updating a review
reviewing a new genre
(eventually) upvotes or hot takes

level 1 - just joined, base
level 2 - reviewed a fair amount of movies, couple genres, had some successful posts
level 3 - reviewed a lot of movies, majority of genres, many successful posts
level 4 - reviewed most movies across all genres, consistent with critics, consistently have good posts
level 5 - extremely coveted reviews, reviews influence other people, review almost every movie across all genres

connect to database
add sign in/ sign up feature for account
Complete User Login and Registration Backend + Email Verification
update cache to most searched instead of recently searched to improve efficacy

FUTURE
things to rate - songs, albums, artists, athletes, teams
social media/discussion forums function
recommendation engine
movie decider based on multiple people's recommendation list
*/

public class Interface {
    static Database database;
    static User currentUser;
    static APIHandler APIHandlerInstance;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // create new scanner to take user input
        database = new Database(); // create new database instance
        APIHandlerInstance = new APIHandler(); // create new API handler instance
        currentUser = new User("exampleUser999", database.getNumUsers()); // eventually add login/sign up functionality
        database.initializeTestAccount(currentUser); // set current user to normal account
        // database.initializeStandardAccount(currentUser);
        // database.initializeProAccount(currentUser);
        boolean loginStatus = true; // login status keeps track of whether there is an account "logged in" or not

        while (loginStatus == true) {

            // Main Menu
            System.out.println();
            System.out.println("Welcome to the Main Menu! (Please enter the option number you want)");
            System.out.println("1. Rate a Movie");
            System.out.println("2. View Profile");
            System.out.println("3. Settings");
            System.out.println("4. Exit Program");

            int mainMenuOption = scan.nextInt(); // user input for main menu option

            switch (mainMenuOption) {
                // Main Menu: Rate a Movie
                case 1:
                    // Rate movie by genre
                    System.out.println("What genre are you rating? (Please enter the option number you want)");
                    System.out.println("1. Action/Adventure (live)");
                    System.out.println("2. Comedy (live)");
                    System.out.println("3. Drama (live)");
                    System.out.println("4. Return to Main Menu");
                    System.out.println("5. Exit Program");

                    int genreOption = scan.nextInt(); // user input for genre option

                    switch (genreOption) {
                        case 1: 
                        case 2:
                        case 3:
                            rateMovie(genreOption); // if case 1, 2 or 3, use rateMovie method and pass in genre selection
                            break;
                        case 4:
                            break; // return to main menu
                        case 5: 
                            loginStatus = false; // change login status to false to stop the loop
                            System.out.println("Goodbye!");
                            break;   
                        default:
                            System.out.println("Invalid input. Please enter the option number only.");
                            break; 
                    }

                    System.out.println("Returning to main menu...");
                    break;

                // Main Menu: Display Profile
                case 2:
                    System.out.println();
                    System.out.println(currentUser.displayProfile());

                    System.out.println("Returning to main menu...");
                    break;

                // Main Menu: Settings
                case 3:
                    // Explain what the user's current settings are in regard to pro/standard account status
                    if (currentUser.isPro() == false) {
                        System.out.println("You currently have a standard account");
                        System.out.println("This means you are shown limited categories to rate and ");
                        System.out.println("descriptions for the available categories.");
                    }
                    else {
                        System.out.println("You currently have a pro account");
                        System.out.println("This means you are shown all categories to rate and ");
                        System.out.println("no descriptions.");
                    }

                    System.out.println();
                    
                    // Give user option to pick account setting
                    System.out.println("Choose an account setting option:");
                    System.out.println("1. Standard Account (Less categories to rate, descriptions included)");
                    System.out.println("2. Pro Account (Access to more categories to rate, no descriptions)");

                    int settingOption = scan.nextInt(); // user input for account setting option

                    switch (settingOption) {
                        // User has selcted standard account, initialize user categories to standard
                        case 1:
                            database.initializeNormalAccount(currentUser);
                            System.out.println("Your settings have been changed to a normal account!");
                            break;

                        // User has selcted pro account, initialize user categories to pro
                        case 2:
                            database.initializeProAccount(currentUser);
                            currentUser.setPro();
                            System.out.println("Your settings have been changed to a pro account!");
                            break;
                        default:
                            System.out.println("Invalid input. Please enter the option number only.");
                            break; 
                    }

                    System.out.println("Returning to main menu...");
                    break;

                // Main Menu: Exit Program
                case 4:
                    loginStatus = false;
                    System.out.println("Goodbye!");
                    break;
                
                default:
                    System.out.println("Invalid input. Please enter the option number only.");
                    break;
            
            } 
        }
        // scan.close();
    }

    // User rates movie based on categories (based on genre and account settings)
    public static void rateMovie(int input) { 

        Scanner scan = new Scanner(System.in); // create new scanner to take user input
        HashMap<String, Double> multipliers; // create hashmap of multipliers, pull from specific user's account

        // User input is deciding which genre they want to rate
        if (input == 1)
            multipliers = currentUser.actionMultipliers;
        else if (input == 2)
            multipliers = currentUser.comedyMultipliers;
        else
            multipliers = currentUser.dramaMultipliers;

        double highestScore = 0; // highestScore variable keeps track of the best possible rating the movie can get
        double userScore = 0; // userScore variable keeps track of the cumulative score the user has given

        // Ask user for the title of the movie they want to rate, remove spaces and use to search IMDB API for options
        System.out.println();
        System.out.println("What movie are you rating?");
        String inputTitle = scan.nextLine();
        inputTitle = inputTitle.replaceAll("\\s", "");

        // Search IMDB APi and returns JSON Array of all the movies that match user input
        JSONArray temp = APIHandlerInstance.searchMovieByTitle(inputTitle);
        ArrayList<String> movieIDs = parse(temp);

        int idSelection = scan.nextInt(); // user input for search results selection
        Movie currentMovie = new Movie(movieIDs.get(idSelection - 1)); // create new movie object using unique id from IMDB API

        System.out.println();
        System.out.println("Rate each category on a 0.0-10.0 scale." + '\n' + "10 being the best you could ever hope for, 5 being completely neutral" + '\n' + "and 0 being the worst you could imagine.");
        System.out.println();

        // Loop through genre's categories, if user has them live, get ratings to make final rating
        for (Map.Entry<String, Category> entry : database.getCategories().entrySet()) {
            String key = entry.getKey(); // category name
            Category value = entry.getValue(); // category obj

            // if statement checks if user has this category active...
            if (multipliers.get(key) != 0) {
                // if description is available and user is not pro, print the description
                if (value.getDescription() != null && currentUser.isPro() == false) {
                    System.out.println(value.getDescription());
                }
                
                System.out.println(value.getQuestion());
                double answer = scan.nextDouble(); // user input for rating on current category

                currentMovie.addRating(key, answer); // add category name and user's rating into movie object's hashmap

                userScore += answer * multipliers.get(key); // add user's rating to running total, factoring in user's weightings
                highestScore += 10 * multipliers.get(key); // keep track of highest score possible to calculate final score later
            }
        }

        // Calculate final score
        userScore /= highestScore;
        userScore *= 10;
        userScore = Math.round(userScore * 100.0) / 100.0;

        // Set movie objects rating, add movie object to user's profile
        currentMovie.setFinalRating(userScore);
        currentUser.addMovie(movieIDs.get(idSelection - 1), currentMovie);
        currentUser.increaseMovieCount();

        // Print final rating
        System.out.println("Your Overall Rating is:");
        System.out.println(userScore + "/10");

        // scan.close();
    }

    // API call returns a JSON Array of the movies that fit search input, parse through that and return organized movie list
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