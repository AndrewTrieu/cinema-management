package src;

import java.util.ArrayList;

// Movie class
public class Movie {
    // attributes
    private String title;
    private String director;
    private int duration;
    private String releaseTime;
    private String review;
    private ArrayList<String> genre;
    private float rating;
    private int numRatings;

    // constructor
    public Movie(String title, String director, int duration, String releaseTime, String review,
            ArrayList<String> genre, float rating,
            int numRatings) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.releaseTime = releaseTime;
        this.review = review;
        this.genre = genre;
        this.rating = rating;
        this.numRatings = numRatings;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public String getReview() {
        return review;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public float getRating() {
        return rating;
    }

    public int getNumRatings() {
        return numRatings;
    }
}
