package src;

import java.util.ArrayList;
import java.util.List;

// Theater class
public class Theater extends Venue {
    // attributes
    protected List<Movie> movies;
    protected List<Showtime> showtimes;
    protected Seat[][] seats;

    // constructor
    public Theater(String name, String location, int rows, int columns) {
        super(name, location);
        this.movies = new ArrayList<>();
        this.showtimes = new ArrayList<>();
        this.seats = new Seat[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = new Seat(i, j, true);
            }
        }
    }

    // methods
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
    }

    public void viewMovie(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Release Year: " + movie.getReleaseYear());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Duration: " + movie.getDuration());
        System.out.println("Genre: " + movie.getGenre());
    }

    public void viewAllMovies() {
        for (Movie movie : movies) {
            viewMovie(movie);
            System.out.println();
        }
    }

    public void addShowtime(Showtime showtime) {
        this.showtimes.add(showtime);
    }

    public void removeShowtime(Showtime showtime) {
        this.showtimes.remove(showtime);
    }

    public void viewShowtime(Showtime showtime) {
        System.out.println("Movie: " + showtime.getMovie().getTitle());
        System.out.println("Time: " + showtime.getTime());
        System.out.println("Date: " + showtime.getDate());
        System.out.println("Price: " + showtime.getPrice());
    }

    public void viewAllShowtimes() {
        for (Showtime showtime : showtimes) {
            viewShowtime(showtime);
            System.out.println();
        }
    }

    public void buyTicket(Showtime showtime, Seat seat) {
        seat.setAvailable(false);
    }

    public void viewSeating(Showtime showtime) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j].isAvailable() ? "[O] " : "[X] ");
            }
            System.out.println();
        }
    }

    public Movie getMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    public Showtime getShowtimeByMovieAndTimeAndDate(Movie movie, String time, String date) {
        for (Showtime showtime : showtimes) {
            if (showtime.getMovie().equals(movie) && showtime.getTime().equals(time)
                    && showtime.getDate().equals(date)) {
                return showtime;
            }
        }
        return null;
    }
}
