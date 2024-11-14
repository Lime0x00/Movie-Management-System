import java.text.SimpleDateFormat;
import java.lang.String;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


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
    //! There is some error movie selectedSeats as Seat cannot book it as it already booked



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
        movies.add(new Movie("Movie 2", enGenre.ROMANCE, 2.15f, screenTimes2));

        var lib = new MovieLibrary(movies);
        // Movie 3 Data
        var screenTimes3 = new ArrayList<ScreenTime>();
        screenTimes3.add(new ScreenTime(hall1, new Date(2024 - 1900, 10, 25, 10, 0, 0), new Date(2024 - 1900, 10, 25, 12, 0, 0)));
        screenTimes3.add(new ScreenTime(hall2, new Date(2024 - 1900, Calendar.DECEMBER, 26, 16, 0, 0), new Date(2024 - 1900, Calendar.DECEMBER, 26, 18, 0, 0)));
        Movie movie3 = new Movie("Movie 3", enGenre.ROMANCE, 1.15f, screenTimes3);

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



    private static boolean confirmMsg (Scanner scanObj, Movie selectedMovie, List<Seat> seats) {
       if (seats.size() > 1) {
           float totalPrice = Order.getPrice(selectedMovie, seats);
           var response = Check.Response.UNKNOWN;

           do {
               //todo: Implement message
               System.out.println("Confirm Payment? ");
               System.out.println("Price all is " + totalPrice);
               String answer = scanObj.next();
               response = Check.getResponse(answer);

           } while(response == Check.Response.UNKNOWN);

           return response == Check.Response.YES;
       }
       return false;
    }

    private static class Print {
        //todo: Add Methods here and to be static
        private static void movieDetails(Movie movie) {
            System.out.flush();
            System.out.println("========================================");
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Booked Seats: " + movie.getBookedSeats());
            System.out.println("========================================");
        }

        private static void seats (Hall movieHall) {
            System.out.println("B => Seat is Already Booked");
            System.out.println("F => Seat is Free");

            var seats = movieHall.getSeats();

            System.out.println("\t\t\t\t\tScreen\t\t\t\t\t");
            System.out.println("\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\t");
            System.out.print("    ");

            for (int i = 1; i <= movieHall.getNumberOfCols(); i++) {
                System.out.print(i + "\t");
            }

            System.out.println();

            for (int row = 0; row < movieHall.getNumberOfRows(); row++) {
                System.out.print((char) ('A' + row) + "\t");
                for (int col = 0; col < movieHall.getNumberOfCols(); col++) {
                    if (seats[row][col].isAvailable()) {
                        System.out.print("\u001B[32mF\u001B[0m\t");
                    } else {
                        System.out.print("\u001B[31mB\u001B[0m\t");
                    }
                }
                System.out.println();
            }
        }

        private static void receipt (Receipt receipt) {
            //todo: Implement way to display receipt
            if (receipt != null) {
                System.out.println("\u001B[32mSuccessful Transication\u001B[0m\t");
                //todo: Add Way to display Receipt
                System.out.println("Name :" + receipt.getCustomerName());
                System.out.println("Movie Name : " + receipt.getMovieName());
                System.out.println("Total Price : " + receipt.getTotalPrice() + " LE");
                System.out.println("Hall ID : " + receipt.getHallID());
                System.out.println("Start Date : ");
            } else {
                System.out.println("\u001B[31mSomething Went Wrong!\u001B[0m\t");
            }
        }

        private static void screenTime(ScreenTime screenTime) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy 'at' HH:mm");

            Date startDate = screenTime.getStartDate();
            Date endDate = screenTime.getEndDate();

            if (startDate != null && endDate != null) {
                System.out.printf(" %s (Duration: %s)%n",
                        dateFormat.format(startDate),
                        Format.duration(startDate, endDate)
                );
            } else {
                System.out.println("Screen time has null dates.");
            }
        }
    }


    private static class Menu {
        static final String[] mainMenu = {
                "Book Movie",
                "Generate Reports",
                "List Receipts",
                "Log-out",
        };

        private static void mainMenu(Scanner scanObj, Customer customer) {
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

        private static void bookMovie (Scanner scanObj, Customer customer) {
            System.out.flush();

            List<Movie> movies = MovieLibrary.getMovies();
            var movie = Select.movie(scanObj);

            var screenTime = Select.screenTime(scanObj, movie);
            var hall = screenTime.getHall();

            var selectedSeats = Select.seats(scanObj, hall);
            //! Selected Seats must be free before booking (as its checking inside Order)

            if (confirmMsg(scanObj, movie, selectedSeats)) {
                var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
                Print.receipt(receipt);
            } else {
                selectedSeats = Select.seats(scanObj, hall);
                Select.unSelectSeats(scanObj, selectedSeats, hall);
                if (confirmMsg(scanObj, movie, selectedSeats)) {
                    var receipt =  customer.bookMovie(screenTime, movie, selectedSeats);
                    Print.receipt(receipt);
                }
            }
        }

        private static void generateReports (Scanner scanObj) {
            final String[] reports = {
                    "Number of Sold Seats",
                    "Report for most crowded time",
                    "Number of Seats on interval",
                    "Report for most watched film",
            };
            /*
             * ex:
             * 1: Number of Sold Seats
             * 2: Number of Seats on interval [02:00 - 04:00]
             * 3: Report for most crowded time
             * 4: Report for most watched film
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
                    System.out.println(Report.getCrowdedTime());
                    break;
//              case 4:
//                    System.out.println(Report.mostWatchedFilm());
//                    break;
            }

        }


        private static void listReceipts (Scanner scanObj, Customer customer) {
            //ps: receipts better to be array for performance
            int choice;
            var receipts = customer.getReceipts();

            if (receipts != null) {
                do {
                    for (int i = 0; i < receipts.size(); i++) {
                        //! if the receipt has id replace getMovieName() with getID (Receipts 1:N Movie)
                        System.out.println((i + 1) + ": " + receipts.get(i).getMovieName());
                    }

                    System.out.println("Enter Which Receipt to display ");
                    choice = scanObj.nextInt();

                } while (!Check.isValidAnswer(choice, receipts));

                Print.receipt(receipts.get(choice - 1));
            }
        }
    }

    private static class Select {
        private static Movie movie(Scanner scanObj) {
            int choice;
            var movies = MovieLibrary.getMovies();

            do {
                System.out.flush();
                int i = 0;
                for (var movie : movies) {
                    System.out.println((i + 1) + ": " + movie.getTitle());
                    i++;
                }

                System.out.print("Choose a movie: ");
                choice = scanObj.nextInt();

            } while (!Check.isValidAnswer(choice, movies));

            return movies.get(choice - 1);
        }

        private static ScreenTime screenTime (Scanner scanObj, Movie movie) {
            int choice;
            var screenTimes = movie.getScreenTimes();
            do {
                System.out.flush();

                Print.movieDetails(movie);

                System.out.println("Available Screen Times:");

                for (int i = 0; i < screenTimes.size(); i++) {
                    var screenTime = screenTimes.get(i);
                    if (!screenTime.getHall().isFull()) { //Check if all seats hasn't been booked yet
                        System.out.print((i + 1) + ": ");
                        Print.screenTime(screenTime);
                    }
                }
                System.out.print("Choose your preferred screen time: ");
                choice = scanObj.nextInt();
            } while (!Check.isValidAnswer(choice, screenTimes));

            return screenTimes.get(choice - 1);
        }


        private static LinkedList<Seat> seats (Scanner scanObj, Hall hall) {
            var seats = hall.getSeats();
            var selectedSeats = new LinkedList<Seat>();
            var response = Check.Response.UNKNOWN;

            do {
                Print.seats(hall);

                System.out.print("Do you want to book a seat? (y/n) ");
                String answer = scanObj.next().trim();
                response = Check.getResponse(answer);

                if (response == Check.Response.NO) {
                    break;
                }

                if (response == Check.Response.UNKNOWN) {
                    continue;
                }

                System.out.print("Enter Seat ID to book (e.g., A1): ");
                String seatID = scanObj.next();

                //! Need to temporarily book seats until payment done
                //! Can define mechanism with timeout to set seats free

                int row = seatID.charAt(0) - 'A';
                int col = Integer.parseInt(seatID.substring(1)) - 1;

                //! Add some protection to row and col
                if (seats[row][col].isAvailable()) {
                    seats[row][col].setAvailability(false);
                    selectedSeats.add(seats[row][col]);
                    System.out.println("\u001B[32mSuccessfully booked seat \"" + seatID + "\"!\u001B[0m");
                } else {
                    System.out.println("\u001B[31mSeat \"" + seatID + "\" is already booked or invalid.\u001B[0m");
                }

            } while (!hall.isFull());


            return selectedSeats;
        }

        private static void unSelectSeats (Scanner scanObj, List<Seat> selectedSeats, Hall hall) {
            var seats = hall.getSeats();
            var response = Check.Response.UNKNOWN;

            do {
                System.out.print("Do you want to cancel booked seat? (y/n) ");
                String answer = scanObj.next();
                response = Check.getResponse(answer);

                if (response == Check.Response.NO) {
                    break;
                }

                if (response == Check.Response.UNKNOWN) {
                    continue;
                }

                System.out.print("Enter Seat ID to cancel (e.g., A1): ");
                String seatID = scanObj.next();

                int row = seatID.charAt(0) - 'A';
                int col = Integer.parseInt(seatID.substring(1)) - 1;

                boolean seatFound = false;

                Iterator<Seat> iterator = selectedSeats.iterator();
                while (iterator.hasNext()) {
                    Seat seat = iterator.next();
                    if (seatID.equals(seat.getID())) {
                        iterator.remove();
                        seats[row][col].setAvailability(true);
                        System.out.println("\u001B[32mSuccessfully removed seat \"" + seatID + "\"!\u001B[0m");
                        seatFound = true;
                        break;
                    }
                }

                if (!seatFound) {
                    System.out.println("\u001B[31mCannot find seat \"" + seatID + "\" among selected seats.\u001B[0m");
                }

            } while (!selectedSeats.isEmpty());
        }


        private static int report (Scanner scanObj, String[] reports) {
            int choice;
            do {
                for (int i = 0; i < reports.length; i++) {
                    System.out.println((i + 1) + ": " + reports[i]);
                }

                System.out.println("Enter Which Report to display");
                choice = scanObj.nextInt();
            } while (!Check.isValidAnswer(choice, reports));

            return choice;
        }
    }

    private static class Format {
        private static String duration (Date startDate, Date endDate) {
            long durationInMillis = endDate.getTime() - startDate.getTime();
            long hours = (durationInMillis / (1000 * 60 * 60)) % 24;
            long minutes = (durationInMillis / (1000 * 60)) % 60;

            return String.format("%d hours and %d minutes", hours, minutes);
        }
    }

    private static class Check {
        private  enum Response {
            YES,
            NO,
            UNKNOWN,
        }

        private static <T> boolean isValidAnswer (int choice, T[] arr) {
            return choice > 0 && choice <= arr.length;
        }

        private static <T> boolean isValidAnswer (int choice, List<T> list) {
            return choice > 0 && choice <= list.size();
        }

        private static Response getResponse(String answer) {
            var normalizedChoice = answer.toLowerCase(Locale.ROOT);
            return switch (normalizedChoice) {
                case "y", "yes" -> Response.YES;
                case "n", "no" -> Response.NO;
                default -> Response.UNKNOWN;
            };
        }

    }

}
