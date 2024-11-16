package Movie;

/**
 * The enGenre enum represents the different genres of movies available in the system.
 * It includes genres such as Action, Romantic, Horror, and Comedy.
 */
public enum enGenre {
    /** Represents an Action genre movie. */
    ACTION("Action"),

    /** Represents a Romantic genre movie. */
    ROMANTIC("Romantic"),

    /** Represents a Horror genre movie. */
    HORROR("Horror"),

    /** Represents a Comedy genre movie. */
    COMEDY("Comedy");

    /** The description of the genre. */
    private final String genre;

    /**
     * Constructs an enGenre instance with the specified genre description.
     *
     * @param genre the genre description to set.
     */
    enGenre(String genre){
        this.genre = genre;
    }

    /**
     * Retrieves the genre description.
     *
     * @return the genre description as a string.
     */
    public String getGenre(){
        return genre;
    }
}
