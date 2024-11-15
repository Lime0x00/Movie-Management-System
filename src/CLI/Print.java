package CLI;
import Movie.Movie;
import Book.Receipt;
import java.text.SimpleDateFormat;
import java.util.Date;
import Hall.Hall;
import Movie.ScreenTime;

public class Print {
    //todo: Add Methods here and to be static
    public static void movieDetails(Movie movie) {
        System.out.flush();
        System.out.println("========================================");
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Duration: " + movie.getDuration());
        System.out.println("Genre: " + movie.getGenre());
        System.out.println("Booked Seats: " + movie.getBookedSeats());
        System.out.println("========================================");
    }

    public static void seats(Hall movieHall) {
        System.out.println("B => Hall.Hall.Seat is Already Booked");
        System.out.println("F => Hall.Hall.Seat is Free");

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

    public static void receipt(Receipt receipt) {
        //todo: Implement way to display receipt
        if (receipt != null) {
            //todo: Add Way to display Book.Receipt
            System.out.println("Name :" + receipt.getCustomerName());
            System.out.println("Movie Name : " + receipt.getMovieName());
            System.out.println("Total Price : " + receipt.getTotalPrice() + " LE");
            System.out.println("Hall ID : " + receipt.getHallID());
            System.out.println("Start Date : ");
        } else {
            System.out.println("\u001B[31mSomething Went Wrong!\u001B[0m\t");
        }
    }

    public static void screenTime(ScreenTime screenTime) {
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
