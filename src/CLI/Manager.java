package CLI;

import Book.Customer;
import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;
import Movie.MovieLibrary;
import Movie.enGenre;

import java.lang.String;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

public class Manager {
    //!TODO
    //todo: Add Packages
    //todo: Move class Receipt to class Order (Composition)
    //todo: Move class seats to class hall (Composition)
    //todo: Make sure not including all the library for one class
    //todo: Remove confirmMsg() from Order (Check it here)
    //todo: Make getPrice() In Order public (In Case Confirm Message here not in Order)

    //!Changes
    //! Added getNumberOfRows() to hall
    //! Added getNumberOfCols() to hall
    //! Added getSeats to hall (Must be changed boolean[][])
    //! There is some error movie selectedSeats as Hall.Hall.Seat cannot book it as it already booked

    public Manager (Scanner scanObj) {
        initializeLibrary();
        start(scanObj);
    }

    private void initializeLibrary () {
        var hall1 = new Hall((byte) 1, 2, 2);  // 10 * 10 = 100 Seat
        var screenTimes1 = new ArrayList<ScreenTime>();

        screenTimes1.add(new ScreenTime(hall1, new Date(2024 - 1900, 10, 21, 12, 20, 30), new Date(2024 - 1900, 10, 21, 14, 20, 30)));

        Movie movie = new Movie("Movie1", enGenre.ROMANTIC, 1.15f, screenTimes1 );
        var movies = new ArrayList<Movie>();
        movies.add(movie);
        MovieLibrary lib = new MovieLibrary(movies);
    }


    public void start (Scanner scanObj) {
        java.lang.System.out.print("Enter your name: ");
        String name = scanObj.next();

        java.lang.System.out.print("Enter your age: ");
        short age = scanObj.nextShort();

        var customer = new Customer(name, age);

        java.lang.System.out.println("Welcome " + customer.getName());

        Menu.mainMenu(scanObj, customer);
    }


}
