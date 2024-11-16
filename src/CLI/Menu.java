/**
 * Provides the main menu interface for the movie booking system and various operations such as booking movies, 
 * generating reports, managing receipts, and handling movies and screen times.
 */
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

public class Menu {
    /**
     * Array of main menu options displayed to the user.
     */
    static final String[] mainMenu = {
            "Book Movie",
            "Generate Reports",
            "List Receipts",
            "Add Movie",
            "Delete Movie",
            "Add Screen Time",
            "Log-out",
    };

    /**
     * Displays the main menu and processes user input for menu options.
     *
     * @param scanObj   the {@link Scanner} object for user input
     * @param customer  the {@link Customer} currently using the system
     */
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
                case 6 -> addScreenTimes(scanObj);
                case 7 -> {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                }
            }
            System.out.println("Enter Any Key to continue");
            scanObj.next();
        }
    }

    /**
     * Handles the movie booking process for a customer.
     *
     * @param scanObj   the {@link Scanner} object for user input
     * @param customer  the {@link Customer} booking the movie
     */
    public static void bookMovie(Scanner scanObj, Customer customer) {
        var movie = Select.movie(scanObj);
        ScreenTime screenTime = Select.screenTime(scanObj, movie);
        var hall = screenTime.getHall();
        var selectedSeats = Select.seats(scanObj, hall);

        if (Check.confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
            var receipt = customer.bookMovie(screenTime, movie, selectedSeats);
            System.out.println("\u001B[32mSuccessful Transaction\u001B[0m\t");
            Print.receipt(receipt);
        } else {
            selectedSeats = Select.seats(scanObj, hall);
            Select.unSelectSeats(scanObj, selectedSeats, hall);
            if (Check.confirmMsg(scanObj, movie, selectedSeats, screenTime)) {
                var receipt = customer.bookMovie(screenTime, movie, selectedSeats);
                System.out.println("\u001B[32mSuccessful Transaction\u001B[0m\t");
                Print.receipt(receipt);
            }
        }
    }

    /**
     * Generates various system reports based on user selection.
     *
     * @param scanObj  the {@link Scanner} object for user input
     */
    public static void generateReports(Scanner scanObj) {
        final String[] reports = {
                "Number of Sold Seats",
                "Most crowded time",
                "Number of Seats on interval",
                "Most watched film",
        };

        int choice = Select.report(scanObj, reports);
        choice--;

        switch (choice) {
            case 0 -> {
                Movie movie = Select.movie(scanObj);
                System.out.println(reports[choice] + " on movie \"" + movie.getTitle() + "\" is " + Report.numberOfSoldSeats(movie));
            }
            case 1 -> {
                var crowdedTimes = Report.getCrowdedTimes();
                for (var time : crowdedTimes) {
                    for (var date : time) {
                        System.out.println("Date is: " + Format.date(date));
                    }
                }
            }
            case 2 -> {
                Movie movie2 = Select.movie(scanObj);
                ScreenTime screenTime = Select.screenTime(scanObj, movie2);
                System.out.println(reports[choice] + " on date start: " + screenTime.getStartDate() + " and end: " + screenTime.getEndDate() +
                        " is " + Report.getNumberOfSeats(screenTime.getStartDate(), screenTime.getEndDate()));
            }
            case 3 -> {
                System.out.println("The most watched movie(s):");
                Print.movies(Report.mostWatchedMovie());
            }
        }
    }

    /**
     * Lists and displays receipts associated with the customer.
     *
     * @param scanObj   the {@link Scanner} object for user input
     * @param customer  the {@link Customer} whose receipts are listed
     */
    public static void listReceipts(Scanner scanObj, Customer customer) {
        int choice;
        var receipts = customer.getReceipts();

        if (receipts != null && !receipts.isEmpty()) {
            do {
                for (int i = 0; i < receipts.size(); i++) {
                    System.out.println((i + 1) + ": #" + receipts.get(i).getID());
                }

                System.out.println("Enter Which Receipt to display: ");
                choice = scanObj.nextInt();

            } while (!Check.isValidAnswer(choice, receipts));

            Print.receipt(receipts.get(choice - 1));
        } else {
            System.out.println("Cannot find any receipts.");
        }
    }

    /**
     * Prompts the user to input a date within a valid range and returns it.
     *
     * @return the valid {@link Date} entered by the user
     */
    public static Date getDateFromUser() {
        Scanner scanner = new Scanner(System.in);
        int year, month, day, hour, minute, second;
        Date date = null;
        boolean validDate = false;

        while (!validDate) {
            System.out.println("Enter the year (e.g., 2024): ");
            year = scanner.nextInt();
            System.out.println("Enter the month (1-12): ");
            month = scanner.nextInt() - 1;
            System.out.println("Enter the day (1-31): ");
            day = scanner.nextInt();
            System.out.println("Enter the hour (0-23): ");
            hour = scanner.nextInt();
            System.out.println("Enter the minute (0-59): ");
            minute = scanner.nextInt();
            System.out.println("Enter the second (0-59): ");
            second = scanner.nextInt();

            date = new Date(year - 1900, month, day, hour, minute, second);

            if (Check.isTodayOrWithinOneWeek(date)) {
                validDate = true;
            } else {
                System.out.println("The date you entered is not today or within one week from now. Please try again.");
            }
        }

        return date;
    }

    /**
     * Adds a new movie to the library based on user input.
     *
     * @param scanObj  the {@link Scanner} object for user input
     */
    public static void addMovie(Scanner scanObj) {
        System.out.print("Enter Movie Title: ");
        scanObj.nextLine();
        String title = scanObj.nextLine();
        enGenre genre = Select.genre(scanObj);
        List<ScreenTime> screenTimes = new ArrayList<>();

        var response = Check.enResponse.YES;

        while (response == Check.enResponse.YES) {
            var hall = Select.selectHall(scanObj);
            Date startDate = getDateFromUser();
            Date endDate = getDateFromUser();

            if (endDate.before(startDate)) {
                System.out.println("Error: End date must be after start date.");
                continue;
            }

            var screenTime = new ScreenTime(hall, startDate, endDate);
            if (screenTimes.stream().anyMatch(st -> st.getHall().equals(hall) &&
                    Check.datesOverlap(startDate, endDate, st.getStartDate(), st.getEndDate()))) {
                System.out.println("Error: ScreenTime overlaps with an existing entry in the same hall.");
            } else {
                screenTimes.add(screenTime);
                System.out.println("Screen Time Added.");
            }

            System.out.print("Do you want to add more screen times? (y/n): ");
            response = Check.getResponse(scanObj.next());
        }

        Movie newMovie = new Movie(title, genre, screenTimes);

        if (MovieLibrary.hasMovie(newMovie)) {
            System.out.println(title + " has already been added before.");
        } else {
            MovieLibrary.addMovie(newMovie);
            System.out.println(title + " has been added successfully.");
        }
    }

    /**
     * Deletes a movie from the library based on user input.
     *
     * @param scanObj  the {@link Scanner} object for user input
     */
    public static void deleteMovie(Scanner scanObj) {
        if (!MovieLibrary.getMovies().isEmpty()) {
            var response = Check.enResponse.YES;
            do {
                System.out.println("Do you want to delete a movie? (y/n): ");
                response = Check.getResponse(scanObj.next());

                if (response == Check.enResponse.NO) {
                    break;
                }

               
