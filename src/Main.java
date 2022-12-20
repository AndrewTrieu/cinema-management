package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Main class
public class Main {
    // attributes
    private static final String API_KEY = "1958c3873a1d102fde2cab155cda98bc";
    private static Theater theater;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // create a theater with 10 rows and 10 columns
        theater = new Theater("LUT Kino", "Yliopistonkatu", 10, 10);

        // create a menu for customers of the theater

        while (true) {
            System.out.println("\nWelcome to the " + theater.getName() + "at" + theater.getLocation() + "!");
            System.out.println("1. Add a movie");
            System.out.println("2. Remove a movie");
            System.out.println("3. View a movie");
            System.out.println("4. View all movies");
            System.out.println("5. Add a showtime");
            System.out.println("6. Remove a showtime");
            System.out.println("7. View a showtime");
            System.out.println("8. View all showtimes");
            System.out.println("9. Buy a ticket");
            System.out.println("10. View seating");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    viewMovie();
                    break;
                case 4:
                    viewAllMovies();
                    break;
                case 5:
                    addShowtime();
                    break;
                case 6:
                    removeShowtime();
                    break;
                case 7:
                    viewShowtime();
                    break;
                case 8:
                    viewAllShowtimes();
                    break;
                case 9:
                    buyTicket();
                    break;
                case 10:
                    viewSeating();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static HashMap<Integer, String> getGenres() {
        HashMap<Integer, String> genres = new HashMap<Integer, String>();
        try {
            // Fetch the genres from the API
            String listGenres = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY;
            URL obj = new URL(listGenres);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // parse the JSON response
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("genres");
                if (jsonArray.length() > 0) {
                    // add the genres to the HashMap
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject movie = jsonArray.getJSONObject(i);
                        genres.put(movie.getInt("id"), movie.getString("name"));
                    }
                }
            } else {
                System.out.println("Failed to fetch genres from the API!");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return genres;
    }

    // add a movie
    public static void addMovie() {
        // Enter the title to search for a movie
        // The director and duration are not available in the API
        System.out.print("Enter the movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the director: ");
        String director = scanner.nextLine();
        System.out.print("Enter the duration: ");
        int duration = scanner.nextInt();
        String releaseTime = "";
        String review = "";
        ArrayList<String> genre = new ArrayList<String>();
        float rating = 0;
        int numReviews = 0;

        // use The Movie Database API to fetch movie details
        try {
            // Fetch the genres from the API
            HashMap<Integer, String> genres = getGenres();
            // construct the API URL
            String temp = title.replaceAll(" ", "+");
            String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query="
                    + temp;
            // make the HTTP request and parse the response
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // parse the JSON response
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                if (jsonArray.length() > 0) {
                    JSONObject movie = jsonArray.getJSONObject(0);
                    // update the movie details with the API response
                    title = movie.getString("title");
                    releaseTime = movie.getString("release_date");
                    rating = movie.getFloat("vote_average");
                    numReviews = movie.getInt("vote_count");
                    review = movie.getString("overview");
                    JSONArray genreIds = movie.getJSONArray("genre_ids");
                    for (int i = 0; i < genreIds.length(); i++) {
                        genre.add(genres.get(genreIds.getInt(i)));
                    }
                }
            } else {
                System.out.println("Failed to fetch movie details from the API!");
                return;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        // create a movie object and add it to the theater's catalog
        Movie movie = new Movie(title, director, duration, releaseTime, review, genre, rating,
                numReviews);
        theater.addMovie(movie);
        System.out.println("Movie added successfully!");
    }

    public static void removeMovie() {
        System.out.print("Enter the title of the movie to remove: ");
        String title = scanner.nextLine();

        // remove the movie from the theater's catalog
        Movie movie = theater.getMovieByTitle(title);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }
        theater.removeMovie(movie);
        System.out.println("Movie removed successfully!");
    }

    public static void viewMovie() {
        System.out.print("Enter the title of the movie to view: ");
        String title = scanner.nextLine();

        // view the movie's details
        Movie movie = theater.getMovieByTitle(title);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }
        theater.viewMovie(movie);
    }

    public static void viewAllMovies() {
        theater.viewAllMovies();
    }

    public static void addShowtime() {
        System.out.print("Enter the title of the movie: ");
        String title = scanner.nextLine();
        System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
        String time = scanner.nextLine();
        System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
        String date = scanner.nextLine();
        System.out.print("Enter the ticket price for the showtime: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        // find the movie in the theater's catalog
        Movie movie = theater.getMovieByTitle(title);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        // create a showtime object and add it to the theater
        Showtime showtime = new Showtime(movie, time, date, price);
        theater.addShowtime(showtime);
        System.out.println("Showtime added successfully!");
    }

    public static void removeShowtime() {
        System.out.print("Enter the title of the movie: ");
        String title = scanner.nextLine();
        System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
        String time = scanner.nextLine();
        System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
        String date = scanner.nextLine();

        // find the movie in the theater's catalog
        Movie movie = theater.getMovieByTitle(title);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        // remove the showtime from the theater
        Showtime showtime = theater.getShowtimeByMovieAndTimeAndDate(movie, time, date);
        if (showtime == null) {
            System.out.println("Showtime not found!");
            return;
        }
        theater.removeShowtime(showtime);
        System.out.println("Showtime removed successfully!");
    }

    public static void viewShowtime() {
        System.out.print("Enter the title of the movie: ");
        String title = scanner.nextLine();
        System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
        String time = scanner.nextLine();
        System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
        String date = scanner.nextLine();

        // find the movie in the theater's catalog
        Movie movie = theater.getMovieByTitle(title);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        // view the showtime's details
        Showtime showtime = theater.getShowtimeByMovieAndTimeAndDate(movie, time, date);
        if (showtime == null) {
            System.out.println("Showtime not found!");
            return;
        }
        theater.viewShowtime(showtime);
    }

    public static void viewAllShowtimes() {
        theater.viewAllShowtimes();
    }

    public static void buyTicket() {
        System.out.print("Enter the movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter the showtime (time): ");
        String time = scanner.nextLine();
        System.out.print("Enter the showtime (date): ");
        String date = scanner.nextLine();
        System.out.print("Enter the seat (row and column): ");
        String seat = scanner.nextLine();

        // find the movie and showtime
        Movie movie = theater.getMovieByTitle(movieTitle);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }
        Showtime showtime = theater.getShowtimeByMovieAndTimeAndDate(movie, time, date);
        if (showtime == null) {
            System.out.println("Showtime not found!");
            return;
        }

        // parse the seat input
        String[] seatParts = seat.split(" ");
        int row = Integer.parseInt(seatParts[0]);
        int column = Integer.parseInt(seatParts[1]);

        // buy the ticket
        Seat seatChosen = theater.seats[row][column];
        theater.buyTicket(showtime, seatChosen);
        System.out.println("Ticket bought!");
    }

    public static void viewSeating() {
        System.out.print("Enter the movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter the showtime (time): ");
        String time = scanner.nextLine();
        System.out.print("Enter the showtime (date): ");
        String date = scanner.nextLine();

        // find the movie and showtime
        Movie movie = theater.getMovieByTitle(movieTitle);
        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }
        Showtime showtime = theater.getShowtimeByMovieAndTimeAndDate(movie, time, date);
        if (showtime == null) {
            System.out.println("Showtime not found!");
            return;
        }

        // view the seating
        theater.viewSeating(showtime);
    }

}
