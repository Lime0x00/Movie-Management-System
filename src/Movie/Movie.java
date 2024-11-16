package Movie;

import java.util.List;

/**
 * Represents a movie with details such as title, genre, duration, and associated screen times.
 * Each movie has a unique ID generated automatically.
 */
public class Movie {
    /** Unique identifier for the movie. */
    private int id;

    /** Static counter to generate unique IDs for movies. */
    private static int lastID = 0;

    /** The title of the movie. */
    private String title;

    /** The genre of the movie, represented as an enumeration. */
    private enGenre genre;

    /** The duration of the movie in hours. */
    private float duration;

    /** List of screen times for the movie. */
    private List<ScreenTime> lsScreenTimes;

    /**
     * Constructs a Movie object with the specified title, genre, and screen times.
     *
     * @param title       the title of the movie.
     * @param genre       the genre of the movie.
     * @param screenTimes a list of screen times for the movie.
     * @throws IllegalArgumentException if the screen times list is empty.
     */
    public Movie(String title, enGenre genre, List<ScreenTime> screenTimes) {
        setID();
        setTitle(title);
        setGenre(genre);
        setScreenTimes(screenTimes);
    }

    /**
     * Automatically sets a unique ID for the movie using a static counter.
     */
    private void setID() {
        id = lastID++;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title the title of the movie.
     */
    private void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the genre of the movie.
     *
     * @param genre the genre of the movie.
     */
    private void setGenre(enGenre genre) {
        this.genre = genre;
    }

    /**
     * Sets the screen times for the movie and calculates its duration based on the first screen time.
     *
     * @param screenTimes a list of screen times for the movie.
     * @throws IllegalArgumentException if the screen times list is empty.
     */
    private void setScreenTimes(List<ScreenTime> screenTimes) {
        if (screenTimes.isEmpty()) {
            throw new IllegalArgumentException("ScreenTimes can't be empty!");
        }
        this.lsScreenTimes = screenTimes;
        this.duration = screenTimes.get(0).getEndDate().getHours() 
                      - screenTimes.get(0).getStartDate().getHours();
    }

    /**
     * Gets the unique ID of the movie.
     *
     * @return the movie's unique ID.
     */
    public int getID() {
        return id;
    }

    /**
     * Gets the title of the movie.
     *
     * @return the movie's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the genre of the movie.
     *
     * @return the genre of the movie as a string.
     */
    public String getGenre() {
        return genre.getGenre();
    }

    /**
     * Gets the duration of the movie.
     *
     * @return the duration of the movie in hours.
     */
    public float getDuration() {
        return duration;
    }

    /**
     * Gets the list of screen times for the movie.
     *
     * @return a list of the movie's screen times.
     */
    public List<ScreenTime> getScreenTimes() {
        return lsScreenTimes;
    }

    /**
     * Calculates the total number of booked seats for all screen times of the movie.
     *
     * @return the total number of booked seats.
     */
    public int getBookedSeats() {
        int bookedSeats = 0;
        for (ScreenTime screenTime : lsScreenTimes) {
            bookedSeats += screenTime.getHall().getBookedSeats();
        }
        return bookedSeats;
    }

    /**
     * Adds a new screen time to the movie's list of screen times.
     *
     * @param screenTime the screen time to add.
     * @return true if the screen time was added successfully, false otherwise.
     */
    public boolean addScreenTime(ScreenTime screenTime) {
        return lsScreenTimes.add(screenTime);
    }

    /**
     * Checks if the movie has a specific screen time.
     *
     * @param screenTime the screen time to check for.
     * @return true if the screen time exists in the list, false otherwise.
     */
    public boolean hasScreenTime(ScreenTime screenTime) {
        return lsScreenTimes.contains(screenTime);
    }
}
