# Movie Theater Management System

This is a Java project that allows the user to manage a movie theater. The project consists of four classes: `Main`, `Venue`, `Theater`, `Seat`,`Movie`, and `Showtime`.

The `Main` class contains the main method and is responsible for the user interface and interaction. It presents a menu with various options, such as adding and removing movies and showtimes, buying tickets, and viewing movie and seating information. The `Main` class also uses __The Movie Database (TMDB)__ API to fetch movie details and populate the Movie objects.

The `Theater` class represents a movie theater. It has several attributes, including a list of `Movie` objects, a list of Showtime objects, and a two-dimensional array of `Seat` objects. The `Theater` class has several methods that allow the user to add, remove, and view movies, showtimes, and seating information. It also has methods that allow the user to buy tickets and find movies and showtimes by title, time, and date.

The `Seat` class represents a seat in a movie theater. It has several attributes, including the seat's row and column number, and a boolean indicating whether the seat is available or not. The `Seat` class has several getter and setter methods that allow the user to access and modify these attributes.

The `Movie` class represents a movie. It has several attributes, including the movie's title, release year, director, duration, and genre. The `Movie` class has several getter and setter methods that allow the user to access and modify these attributes.

The `Showtime` class represents a showtime for a movie in a movie theater. It has several attributes, including the `Movie` object for the movie, the showtime's time and date, and the ticket price. The `Showtime` class has several getter and setter methods that allow the user to access and modify these attributes.

The `Venue` class is a simple class that represents a venue such as a theater, stadium, or arena. It has name and location attributes and corresponding get and set methods. It is intended to be used as a base class that other classes such as `Theater` can extend to inherit its attributes and methods.

## Object-Oriented Programming Principles
The project uses the four principles of object-oriented programming (OOP):

- The principle of __Abstraction__ is used in the `Movie`, `Showtime`, and `Seat` classes, which provide a high-level interface for working with movie, showtime, and seat objects. This allows the user to interact with these objects without having to worry about the underlying details of their implementation.

- The principle of __Encapsulation__ is used in the `Movie`, `Showtime`, and `Seat` classes, which define their attributes as private and provide public getter and setter methods for accessing and modifying these attributes. This allows the class to control how its attributes can be accessed and modified, which can help prevent data corruption and maintain the integrity of the objects.

- The principle of __Inheritance__ is used in the `Theater` class, which extends the `Venue` class. This allows the Theater class to inherit the attributes and methods of the `Venue` class, which makes it easier to access the name and the location of the theater.

- The principle of __Polymorphism__ is used in the `Theater` class, which contains a list of `Movie` objects and a list of `Showtime` objects. This allows the `Theater` class to treat these objects as if they were of the same type, which allows it to use the same methods (such as add and remove) to manage both types of objects.

## Notes

- This solution is intended to be a reference only. It is not intended to be a complete solution that will work for all possible inputs. It is possible to break the program by entering invalid inputs or by modifying the data files directly. The program is not intended to be robust enough to handle all possible inputs or to recover from all possible errors.
- The project uses the __The Movie Database (TMDB)__ API to fetch movie details and populate the `Movie` objects. The API key is stored in the `API_KEY` variable. To run the project, you must set this variable to your TMDB API key. You can get an API key by signing up for a free TMDB account.
- JSON files are used to handle the data obtained using the API. Make sure to add the `json-xxx.jar` file to the project's classpath.
- The `TmdbExample` class is used to test the TMDB API. It is not part of the project. It is included in the project only for testing purposes.
