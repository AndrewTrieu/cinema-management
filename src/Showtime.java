package src;

// Showtime class
public class Showtime {
    // attributes
    private Movie movie;
    private String time;
    private String date;
    private double price;

    // constructor
    public Showtime(Movie movie, String time, String date, double price) {
        this.movie = movie;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    // getters and setters
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}