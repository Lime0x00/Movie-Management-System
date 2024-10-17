import java.util.Date;
import java.util.List;

public class Report {
    public static int numberOfSoldSeats (Movie movie) {
        return movie.getBookedSeats();
    } 
    
    public static int getNumberOfSeatsInTime (Date startDate, Date endDate) {
        int sumBookedSeats = 0;
        for (Movie movie : MovieStore.getMovies()) {
            for (ScreenTime screenTime : movie.getScreenTimes()) {
                if (screenTime.getStartDate() == startDate && screenTime.getEndDate() == endDate) {
                    sumBookedSeats += screenTime.getBookedSeats();
                }
            }
        }
        return sumBookedSeats;
    }
    
    public static Date[] getCrowdedTime () {
        Date[] mostCrowded = new Date[2];
        int max = 0; 
        int tempMax = 0;
        for (Movie movie : MovieStore.getMovies()) {
            for (ScreenTime screenTime : movie.getScreenTimes()) {
                tempMax = getNumberOfSeatsInTime(screenTime.getStartDate(), screenTime.getEndDate())
                if (max < tempMax) {
                    max = tempMax;
                    mostCrowded[0] = screenTime.getStartDate();
                    mostCrowded[1] = screenTime.getEndDate();
                }
            }
        }
        return mostCrowded;
    }
}
