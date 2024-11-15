package CLI;

import Book.Customer;
import Book.Report;
import Movie.Movie;
import Movie.ScreenTime;
import java.util.*;

public class Menu {

    static final String[] mainMenu = {
            "Book Movie.Movie",
            "Generate Reports",
            "List Receipts",
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
                case 4 -> {
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
            Print.receipt(receipt);
        } else {
            selectedSeats = Select.seats(scanObj, hall);
            Select.unSelectSeats(scanObj, selectedSeats, hall);
            if (confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
                var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
                Print.receipt(receipt);
            }
        }
    }

    public static void generateReports (Scanner scanObj) {
        final String[] reports = {
                "Number of Sold Seats",
                "Book.Report for most crowded time",
                "Number of Seats on interval",
                "Book.Report for most watched film",
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

        System.out.print(reports[choice]);

        switch (choice) {
            case 1:
                Movie movie = Select.movie(scanObj);
                System.out.println(" on movie \"" + movie.getTitle() + "\" is " + Report.numberOfSoldSeats(movie));
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                var crowdedTimes = Report.getCrowdedTimes();
//                    for (var list : crowdedTimes) {
//                        for (var dt : list) {
//                           System.out.println("Start Date" + dt[0]);
//                           System.out.println("End Date" + dt[1]);
//                        }
//                    }
                break;
//              case 4:
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

        if (receipts != null) {
            do {
                for (int i = 0; i < receipts.size(); i++) {
                    System.out.println((i + 1) + ": #" + receipts.get(i).getID());
                }

                System.out.println("Enter Which Book.Receipt to display ");
                choice = scanObj.nextInt();

            } while (!Check.isValidAnswer(choice, receipts));

            Print.receipt(receipts.get(choice - 1));
        }
    }
}