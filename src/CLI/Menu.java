package CLI;

import Book.Customer;
import Book.Order;
import Book.Report;
import Hall.Seat;
import Movie.Movie;
import Movie.ScreenTime;
import java.util.*;

public class Menu {
    static final String[] mainMenu = {
            "Book Movie.Movie",
            "Generate Reports",
            "List Receipts",
            "Add Movie",
            "Log-out",
    };

    public static void mainMenu(Scanner scanObj, Customer customer) {
        while (true) {
            System.out.flush();
            for (int i = 0; i < Menu.mainMenu.length; i++) {
                System.out.println((i + 1) + ": " + Menu.mainMenu[i]);
            }

            System.out.print("Choose An Option: ");
            int choice = scanObj.nextInt();

            if (!Check.isValidAnswer(choice, Menu.mainMenu)) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (choice) {
                case 1 -> bookMovie(scanObj, customer);
                case 2 -> generateReports(scanObj);
                case 3 -> listReceipts(scanObj, customer);
                case 4 -> addMovie(scanObj);
                case 5 -> {
                    return;
                }
            }
            System.out.println("Enter Any Key to continue");
            scanObj.next();
        }
    }

    public static void bookMovie (Scanner scanObj, Customer customer) {
        System.out.flush();

        var movie = Select.movie(scanObj);

        ScreenTime screenTime = Select.screenTime(scanObj, movie);
        var hall = screenTime.getHall();

        var selectedSeats = Select.seats(scanObj, hall);

        if (confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
            var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
            System.out.println("\u001B[32mSuccessful Transication\u001B[0m\t");
            Print.receipt(receipt);
        } else {
            selectedSeats = Select.seats(scanObj, hall);
            Select.unSelectSeats(scanObj, selectedSeats, hall);
            if (confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
                var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
                System.out.println("\u001B[32mSuccessful Transication\u001B[0m\t");
                Print.receipt(receipt);
            }
        }
    }

    public static boolean confirmMsg (Scanner scanObj, Movie selectedMovie, List<Seat> seats, ScreenTime screenTime) {
        if (!seats.isEmpty()) {
            float totalPrice = Order.getPrice(seats);
            var response = Check.enResponse.UNKNOWN;

            do {
                System.out.println("Price all is " + totalPrice);
                System.out.println("Movie Title: " + selectedMovie.getTitle());
                System.out.println("Hall ID: " + screenTime.getHall().getID());
                System.out.print("Screen Time: ");
                Print.screenTime(screenTime);

                System.out.println("Seats: ");
                for (var seat : seats) {
                    System.out.print(seat.getID() + ", ");
                }
                System.out.println();

                System.out.println("Confirm Payment? (y/n)");
                String answer = scanObj.next();
                response = Check.getResponse(answer);

            } while(response == Check.enResponse.UNKNOWN);

            return response == Check.enResponse.YES;
        }
        return false;
    }

    public static void generateReports (Scanner scanObj) {
        final String[] reports = {
                "Number of Sold Seats",
                "Most crowded time",
                "Number of Seats on interval",
                "Most watched film",
        };
        /*
         * ex:
         * 1: Number of Sold Seats
         * 2: Number of Seats on interval [02:00 - 04:00]
         * 3: Book.Report for most crowded time
         * 4: Book.Report for most watched film
         * etc.
         * */

        //todo: Add more reports

        int choice = Select.report(scanObj, reports);
        choice--;
        System.out.print(reports[choice]);

        switch (choice) {
            case 0:
                Movie movie = Select.movie(scanObj);
                System.out.println(" on movie \"" + movie.getTitle() + "\" is " + Report.numberOfSoldSeats(movie));
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                var crowdedTimes = Report.getCrowdedTimes();
//                    for (var list : crowdedTimes) {
//                        for (var dt : list) {
//                           System.out.println("Start Date" + dt[0]);
//                           System.out.println("End Date" + dt[1]);
//                        }
//                    }
                break;
//              case 3:
//                    System.out.println(Book.Report.mostWatchedFilm());
//                    break;
        }

    }


    public static void listReceipts (Scanner scanObj, Customer customer) {
        //ps: receipts better to be array for performance
        int choice;
        var receipts = customer.getReceipts();

        /*
         * 1: Recipet #12
         * 2: Reciept
         * */

        if (receipts != null && !receipts.isEmpty()) {
            do {
                for (int i = 0; i < receipts.size(); i++) {
                    System.out.println((i + 1) + ": #" + receipts.get(i).getID());
                }

                System.out.println("Enter Which Receipt to display ");
                choice = scanObj.nextInt();

            } while (!Check.isValidAnswer(choice, receipts));

            Print.receipt(receipts.get(choice - 1));
        }
    }

    public static void addMovie (Scanner scanObj) {
        String title = scanObj.next();
        float duration = scanObj.nextFloat();
    }
}