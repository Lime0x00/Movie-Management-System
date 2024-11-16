/**
 * Utility class for formatting durations and dates.
 */
package CLI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

    /**
     * Formats a duration in hours into a human-readable string representation.
     *
     * @param durationInHours the duration in hours
     * @return a string representation of the duration, or "Invalid duration" if the input is invalid
     */
    public static String duration(double durationInHours) {
        if (durationInHours <= 0) {
            return "Invalid duration";
        }

        if (durationInHours < 1) {
            long minutes = Math.round(durationInHours * 60);
            return minutes + (minutes == 1 ? " minute" : " minutes");
        }

        long hours = (long) durationInHours;
        long minutes = Math.round((durationInHours - hours) * 60);

        if (minutes == 0) {
            return hours + (hours == 1 ? " hour" : " hours");
        } else {
            return String.format("%d %s, %d %s",
                    hours, (hours == 1 ? "hour" : "hours"),
                    minutes, (minutes == 1 ? "minute" : "minutes"));
        }
    }

    /**
     * Formats the duration between two {@link Date} objects into a human-readable string representation.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return a string representation of the duration, or "Invalid duration" if the dates are invalid
     */
    public static String duration(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return "Invalid dates";
        }

        long durationInMillis = endDate.getTime() - startDate.getTime();
        if (durationInMillis < 0) {
            return "Invalid duration";
        }

        double durationInHours = durationInMillis / (1000.0 * 60 * 60);

        return duration(durationInHours);
    }

    /**
     * Formats a {@link Date} object into a human-readable string representation.
     *
     * @param date the date to format
     * @return a string representation of the date in the format "dd MMM yyyy at HH:mm"
     */
    public static String date(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy 'at' HH:mm");
        return dateFormat.format(date);
    }
}
