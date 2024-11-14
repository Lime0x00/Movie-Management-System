package CLI;

import java.util.Date;

public class Format {
    public static String duration(Date startDate, Date endDate) {
        long durationInMillis = endDate.getTime() - startDate.getTime();
        long hours = (durationInMillis / (1000 * 60 * 60)) % 24;
        long minutes = (durationInMillis / (1000 * 60)) % 60;

        return String.format("%d hours and %d minutes", hours, minutes);
    }
}
