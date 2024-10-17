import java.util.List;

public class Order {
    public static Receipt bookScreenTime (int userID, ScreenTime screenTime, Movie movie, List<Seat> seats) {
        if (confirmMsg(getPrice(movie, seats), movie, screenTime, seats)) {
            if (MovieStore.hasMovie(movie) && movie.hasScreenTime(screenTime)) {
                Hall hall = screenTime.getHall();

                if (seats.size() > hall.getTotalSeats() - hall.getBookedSeats()) {
                    // Cannot Do
                    return null;
                } else {
                    bookSeats(hall, seats);
                }
            }
        }
        //return new Receipt();
    }
    private boolean bookSeats (Hall hall, List<Seat> seats) {
        for (Seat seat : seats) {
           if (!hall.bookSeat(seat.getID())) {
              return false;
           }
        }
        return true;
    }
    
    private static boolean confirmMsg (float totalPrice, Movie movie, ScreenTime screenTime, List<Seat> seats) {
      //Draw GUI  
        //Placeholder
        return true;
    }
    
    private static float getPrice (Movie movie, List<Seat> seats) {
        float totalPrice = 0;
        for (Seat seat : seats) {
            totalPrice += seat.getPrice();
        }
        return totalPrice;
    }
}
