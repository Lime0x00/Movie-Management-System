package CLI;

import Book.Customer;
import Book.Order;
import Hall.Seat;
import Movie.Movie;

import java.util.List;
import java.util.Scanner;

public class MovieCLI {

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
