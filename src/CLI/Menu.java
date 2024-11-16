package CLI;

import Book.Customer;
import Book.Report;
import Movie.Movie;
import Movie.MovieLibrary;
import Movie.ScreenTime;
import Movie.enGenre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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
                case 5 -> deleteMovie(scanObj);
                case 6 -> {
                    System.out.println("Exiting the program...");
                    System.exit(0); // Close the CLI (terminate the program)
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
        } else {
            System.out.println("Cannot find any receipts");
        }
    }

    public static Date getDateFromUser() {
        Scanner scanner = new Scanner(System.in);

        int year, month, day, hour, minute, second;
        Date date = null;
        boolean validDate = false;

        while (!validDate) {
            System.out.println("Enter the year (e.g., 2024): ");
            year = scanner.nextInt();

            System.out.println("Enter the month (1-12): ");
            month = scanner.nextInt() - 1; // Subtract 1 because months are 0-based in `Date`

            System.out.println("Enter the day (1-31): ");
            day = scanner.nextInt();

            System.out.println("Enter the hour (0-23): ");
            hour = scanner.nextInt();

            System.out.println("Enter the minute (0-59): ");
            minute = scanner.nextInt();

            System.out.println("Enter the second (0-59): ");
            second = scanner.nextInt();

            // Constructing the Date object
            date = new Date(year - 1900, month, day, hour, minute, second);

            // Check if the date is today or within one week
            if (isTodayOrWithinOneWeek(date)) {
                validDate = true;
            } else {
                System.out.println("The date you entered is not today or within one week from now. Please try again.");
            }
        }

        return date;
    }

    public static boolean isTodayOrWithinOneWeek(Date date) {
        // Get the current time
        Date now = new Date();

        // Calculate one week from now
        Date oneWeekFromNow = new Date(now.getTime() + (7L * 24 * 60 * 60 * 1000)); // 7 days in milliseconds

        // Check if the date is today or within the next 7 days
        return !date.before(now) && !date.after(oneWeekFromNow);
    }

    public static void addMovie (Scanner scanObj) {
        System.out.print("Enter Movie Title: ");
        String title = scanObj.nextLine();
        enGenre genre = Select.genre(scanObj);
        List<ScreenTime> screenTimes =  new ArrayList<>();

        Movie newMovie = new Movie(title, genre, screenTimes);

        if (MovieLibrary.hasMovie(newMovie)){
            System.out.println(title + " Already added Before");
            
        }else{
            MovieLibrary.addMovie(newMovie);
            System.out.println(title + " has been Added Successfully.");
        }
    }

    public static void deleteMovie (Scanner scanObj) {
        if (!MovieLibrary.getMovies().isEmpty()) {
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
                
                if (!MovieLibrary.hasMovie(movie)) {
                    System.out.println("\u001B[31mError: Movie \"" + movie.getTitle() + "\" not found.\u001B[0m");
                    continue;
                }

                System.out.println("Are you sure you want to delete the movie \"" + movie.getTitle() + "\"? (y/n)");
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
                    default:
                        System.out.println("something wrong cannot Delete this movie.");
                        break;
                }
            } while (response == Check.enResponse.YES && !MovieLibrary.getMovies().isEmpty());
        }else{
            System.out.println("\u001B[31mError: No movies available to delete.\u001B[0m");
        }
    }
}
