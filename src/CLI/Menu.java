package CLI;

import Book.Customer;
import Book.Report;
import Movie.Movie;
import Movie.MovieLibrary;
import Movie.ScreenTime;

import java.util.Scanner;

public class Menu {
    static final String[] mainMenu = {
            "Book Movie",
            "Generate Reports",
            "List Receipts",
            "Add Movie",
            "Delete Movie",
            "Log-out",
    };

    public static void mainMenu(Scanner scanObj, Customer customer) {
        while (true) {
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
                case 6 -> deleteMovie(scanObj);
                case 5 -> {
                    return;
                }
            }
            System.out.println("Enter Any Key to continue");
            scanObj.next();
        }
    }

    public static void bookMovie (Scanner scanObj, Customer customer) {
        var movie = Select.movie(scanObj);

        ScreenTime screenTime = Select.screenTime(scanObj, movie);
        var hall = screenTime.getHall();

        var selectedSeats = Select.seats(scanObj, hall);

        if (Check.confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
            var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
            System.out.println("\u001B[32mSuccessful Transication\u001B[0m\t");
            Print.receipt(receipt);
        } else {
            selectedSeats = Select.seats(scanObj, hall);
            Select.unSelectSeats(scanObj, selectedSeats, hall);
            if (Check.confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
                var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
                System.out.println("\u001B[32mSuccessful Transication\u001B[0m\t");
                Print.receipt(receipt);
            }
        }
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

        switch (choice) {
            case 0:
                Movie movie = Select.movie(scanObj);
                System.out.println(reports[choice] + " on movie \"" + movie.getTitle() + "\" is " + Report.numberOfSoldSeats(movie));
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
        int choice;
        var receipts = customer.getReceipts();

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
        //todo: Implement Adding Movie code
    }

    public static void deleteMovie (Scanner scanObj) {
       boolean isEmpty = MovieLibrary.getMovies().isEmpty();
       if (!isEmpty) {
           var response = Check.enResponse.YES;
           do {
               System.out.println("Do you want to delete movie (y/n)");
               response = Check.getResponse(scanObj.next());

               if (response == Check.enResponse.UNKNOWN) {
                   System.out.println("Invalid option. Please try again.");
                   continue;
               }

               if (response == Check.enResponse.NO) {
                   break;
               }

               var movie = Select.movie(scanObj);

               System.out.println("Are you sure you want delete "+ movie.getTitle() + " movie ?"+" Y/N" );
               response = Check.getResponse(scanObj.next());

               switch (response) {
                   case YES:
                       if (MovieLibrary.deleteMovie(movie)) {
                           System.out.println(movie.getTitle() + " has been deleted Successfully.");
                           System.out.println("\u001B[32mMovie \"" + movie.getTitle() +"\" Deleted Successfully\u001B[0m\t");
                       } else {
                           System.out.println("\u001B[32mSomething Went Wrong Cannot Delete This Movie\u001B[0m\t");
                       }
                       break;
                   case NO:
                       System.out.println("Error: Deletion canceled.");
                        break;
               }
           } while (response == Check.enResponse.YES && !MovieLibrary.getMovies().isEmpty());
       }
    }
}