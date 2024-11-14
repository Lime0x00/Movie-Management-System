import Book.Customer;
import Book.Order;
import CLI.Menu;
import Hall.Hall;
import Hall.Seat;
import Movie.Movie;
import Movie.ScreenTime;
import Movie.MovieLibrary;
import Movie.enGenre;
import CLI.Check;

import java.lang.String;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;


public class Main {

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



    private static void initializeLibrary () {
        var hall1 = new Hall((byte) 1, 10, 10);  // 10 * 10 = 100 Seat
        var hall2 = new Hall((byte) 2, 20, 20);  // 20 * 20 = 400 Seat
        var hall3 = new Hall((byte) 3, 30, 6);  // 30 * 6 = 180 Seat
        var hall4 = new Hall((byte) 4, 2, 2);

        //Testing if the hall is already full
        //SHOULD NOT BE LISTED IN SCREEN TIMES
        hall4.bookSeat("A1");
        hall4.bookSeat("A2");
        hall4.bookSeat("B1");
        hall4.bookSeat("B2");


        // Movie 1 Data
        var screenTimes1 = new ArrayList<ScreenTime>();
        screenTimes1.add(new ScreenTime(hall1, new Date(2024 - 1900, 10, 21, 12, 20, 30), new Date(2024 - 1900, 10, 21, 14, 20, 30)));
        screenTimes1.add(new ScreenTime(hall2, new Date(2024 - 1900, 10, 22, 15, 0, 0), new Date(2024 - 1900, 10, 22, 17, 0, 0)));
        screenTimes1.add(new ScreenTime(hall4, new Date(2024 - 1900, 10, 22, 15, 0, 0), new Date(2024 - 1900, 10, 22, 15, 0, 0)));

        // Movie 2 Data
        var screenTimes2 = new ArrayList<ScreenTime>();
        screenTimes2.add(new ScreenTime(hall3, new Date(2024 - 1900, 10, 23, 18, 0, 0), new Date(2024 - 1900, 10, 23, 20, 0, 0)));
        screenTimes2.add(new ScreenTime(hall2, new Date(2024 - 1900, 10, 24, 13, 30, 0), new Date(2024 - 1900, 10, 24, 15, 30, 0)));

        // Start Library
        var movies = new ArrayList<Movie>();
        movies.add(new Movie("Movie 1", enGenre.ACTION, 1.30f, screenTimes1));
        movies.add(new Movie("Movie 2", enGenre.ROMANTIC, 2.15f, screenTimes2));

        var lib = new MovieLibrary(movies);
        // Movie 3 Data
        var screenTimes3 = new ArrayList<ScreenTime>();
        screenTimes3.add(new ScreenTime(hall1, new Date(2024 - 1900, 10, 25, 10, 0, 0), new Date(2024 - 1900, 10, 25, 12, 0, 0)));
        screenTimes3.add(new ScreenTime(hall2, new Date(2024 - 1900, Calendar.DECEMBER, 26, 16, 0, 0), new Date(2024 - 1900, Calendar.DECEMBER, 26, 18, 0, 0)));
        Movie movie3 = new Movie("Movie 3", enGenre.ROMANTIC, 1.15f, screenTimes3);

        // Add Movie by Library after initializing
        MovieLibrary.addMovie(movie3);
    }

    public static void main (String[] args) {
        initializeLibrary();

        Scanner scanObj = new Scanner(System.in);

        start(scanObj);

        scanObj.close();
    }


    private static void start (Scanner scanObj) {
        System.out.print("Enter your name: ");
        String name = scanObj.next();

        System.out.print("Enter your age: ");
        short age = scanObj.nextShort();

        var customer = new Customer(name, age);

        System.out.println("Welcome " + customer.getName());

        Menu.mainMenu(scanObj, customer);
    }



    public static boolean confirmMsg (Scanner scanObj, Movie selectedMovie, List<Seat> seats, ScreenTime screenTime) {
       if (seats.size() > 1) {
           float totalPrice = Order.getPrice(seats);
           var response = Check.Response.UNKNOWN;

           do {
               System.out.println("Confirm Payment? ");
               System.out.println("Price all is " + totalPrice);
               System.out.println("Movie.Movie:" + selectedMovie.getTitle());
               System.out.println("Hall.Hall: " + screenTime.getHall().getID());
               //System.out.println("Screen Time: " + );

               System.out.println("Seats: ");
               for (var seat : seats) {
                   System.out.print(seat.getID() + ", ");
               }
               System.out.println();

               String answer = scanObj.next();
               response = Check.getResponse(answer);

           } while(response == Check.Response.UNKNOWN);

           return response == Check.Response.YES;
       }
       return false;
    }

}
