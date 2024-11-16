/**
 * Enum representing the classification of seats in a movie hall.
 * Each seat class has a corresponding description.
 */
public enum enClass {
    /** Represents the first-class seats with premium features. */
    FIRST("First class"),
    
    /** Represents the second-class seats with standard features. */
    SECOND("Second class"),
    
    /** Represents the third-class seats with basic features. */
    THIRD("Third class");

    /** The descriptive text for the seat class. */
    private final String description;

    /**
     * Constructs an enClass enum value with the given description.
     *
     * @param description the description of the seat class.
     */
    enClass(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of the seat class.
     *
     * @return the description of the seat class.
     */
    public String getDescription() {
        return description;
    }
}
