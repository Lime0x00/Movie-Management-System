package Movie;

import Hall.Hall;

import java.util.Date;

/**
 * The ScreenTime class represents a specific screening time for a movie in a particular hall.
 * It includes information about the hall, the screening's start date, and end date.
 * Defensive programming principles are applied to ensure valid data.
 */
public class ScreenTime {
    /** The hall where the screening will be held. */
    private Hall hall;

    /** The start date and time of the screening. */
    private Date startDate;

    /** The end date and time of the screening. */
    private Date endDate;

    /**
     * Constructs a ScreenTime instance with the specified hall, start date, and end date.
     *
     * @param hall      the hall where the screening is held. Must not be null.
     * @param startDate the start date and time of the screening. Must not be null.
     * @param endDate   the end date and time of the screening. Must not be null and must occur after the start date.
     * @throws IllegalArgumentException if any parameter is null or the start date is after the end date.
     */
    public ScreenTime(Hall hall, Date startDate, Date endDate) {
        setHall(hall);
        setStartDate(startDate);
        setEndDate(endDate);
        validateDates();
    }

    /**
     * Sets the hall for this ScreenTime.
     *
     * @param hall the hall to set. Must not be null.
     * @throws IllegalArgumentException if the hall is null.
     */
    public void setHall(Hall hall) {
        if (hall == null) {
            throw new IllegalArgumentException("Hall cannot be null.");
        }
        this.hall = new Hall(hall); // Creates a new Hall instance to ensure immutability of the original object.
    }

    /**
     * Sets the start date for this ScreenTime.
     *
     * @param startDate the start date to set. Must not be null.
     * @throws IllegalArgumentException if the start date is null.
     */
    public void setStartDate(Date startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        this.startDate = startDate;
    }

    /**
     * Sets the end date for this ScreenTime.
     *
     * @param endDate the end date to set. Must not be null.
     * @throws IllegalArgumentException if the end date is null.
     */
    public void setEndDate(Date endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        this.endDate = endDate;
    }

    /**
     * Validates that the start date is before the end date.
     *
     * @throws IllegalArgumentException if the start date is after the end date.
     */
    private void validateDates() {
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }

    /**
     * Retrieves the hall for this ScreenTime.
     *
     * @return the hall where the screening is held.
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * Retrieves the start date for this ScreenTime.
     *
     * @return the start date and time of the screening.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date for this ScreenTime.
     *
     * @return the end date and time of the screening.
     */
    public Date getEndDate() {
        return endDate;
    }
}
