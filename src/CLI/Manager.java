/**
 * Manages the initialization of resources and the main workflow for the movie booking system.
 */
package CLI;

import Book.Customer;
import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;
import Movie.MovieLibrary;
import Movie.enGenre;

import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

public class Manager {
    
    /**
     * Static list of available halls in the system.
     */
    static public ArrayList<Hall> halls = new ArrayList<Hall>();

    /**
     * Constructs a Manager instance, initializes the movie library, and starts the workflow.
     *
     * @param scanObj the {@link Scanner} object for user input
     */
    public Manager(Scanner scanObj) {
        initializeLibrary();
        start(scanObj);
    }

    /**
     * Initializes the library of movies, halls, and their associated screen times.
     * Sets up test data for demonstration purposes.
     */
    private void initializeLibrary() {
        var hall1 = new Hall((byte) 1, 2, 2);  // Hall with 2x2 = 4 seats
        var hall2 = new Hall((byte) 1, 5, 5);  // Hall with 5x5 = 25 seats
        var screenTimes1 = new ArrayList<ScreenTime>();
        var screenTimes2 = new ArrayList<ScreenTime>();

        halls.add(hall1);
        halls.add(hall2);

        screenTimes1.add(new ScreenTime(hall1, new Date(2024 - 1900, 10, 21, 12, 20, 30), new Date(2024 - 1900, 10, 21, 14, 20, 30)));
        screenTimes2.add(new ScreenTime(hall1, new Date(2024 - 1900, 11, 21, 1, 0, 0), new Date(2024 - 1900, 11, 21, 2, 0, 0)));
        screenTimes2.add(new ScreenTime(hall2, new Date(2024 - 1900, 11, 22, 1, 0, 0), new Date(2024 - 1900, 11, 22, 2, 0, 0)));

        Movie movie = new Movie("Movie1", enGenre.ROMANTIC, screenTimes1);
        Movie movie1 = new Movie("Movie2", enGenre.COMEDY, screenTimes2);

        var movies = new ArrayList<Movie>();
        movies.add(movie);
        movies.add(movie1);

        MovieLibrary lib = new MovieLibrary(movies);
    }

    /**
     * Starts the system's main workflow, collecting user input to create a new customer and displaying the main menu.
     *
     * @param scanObj the {@link Scanner} object for user input
     */
    public void start(Scanner scanObj) {
        System.out.print("Enter your name: ");
        String name = scanObj.next();

        System.out.print("Enter your age: ");
        short age = scanObj.nextShort();

        var customer = new Customer(name, age);

        System.out.println("Welcome " + customer.getName());

        Menu.mainMenu(scanObj, customer);
    }
}
