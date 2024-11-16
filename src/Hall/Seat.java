/**
 * Represents a seat in a movie hall with attributes such as an ID, availability status, 
 * price, and class type. The class type determines the price of the seat.
 */
public class Seat {
    /** Unique identifier for the seat (e.g., "A1"). */
    private String id;
    
    /** Indicates whether the seat is available for booking. Default is true. */
    private boolean isAvailable = true;
    
    /** Price of the seat based on its class type. */
    private float price;
    
    /** Class type of the seat, which can be FIRST, SECOND, or THIRD. */
    private enClass classType;

    /**
     * Sets the unique identifier for the seat.
     * 
     * @param id the identifier for the seat (e.g., "A1").
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Sets the class type for the seat. The class type affects the price of the seat.
     * 
     * @param classType the class type of the seat (e.g., FIRST, SECOND, THIRD).
     */
    public void setClass(enClass classType) {
        this.classType = classType;
        setPrice();
    }

    /**
     * Sets the availability status of the seat.
     * 
     * @param isAvailable true if the seat is available for booking, false otherwise.
     */
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Sets the price of the seat based on its class type. 
     * The price is predefined for each class:
     * <ul>
     *   <li>FIRST: 200.50</li>
     *   <li>SECOND: 250.95</li>
     *   <li>THIRD: 350.00</li>
     * </ul>
     * Throws an {@link AssertionError} if the class type is unknown.
     */
    private void setPrice() {
        switch (classType) {
            case FIRST -> price = 200.50F;
            case SECOND -> price = 250.95F;
            case THIRD -> price = 350.00F;
            default -> throw new AssertionError("Seat Class is Unknown.");
        }
    }

    /**
     * Gets the unique identifier for the seat.
     * 
     * @return the seat's unique ID.
     */
    public String getID() {
        return id;
    }

    /**
     * Gets the class type of the seat as a string description.
     * 
     * @return the description of the seat's class type.
     */
    public String getClassType() {
        return classType.getDescription();
    }

    /**
     * Gets the price of the seat based on its class type.
     * 
     * @return the price of the seat.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Checks if the seat is available for booking.
     * 
     * @return true if the seat is available, false otherwise.
     */
    public boolean isAvailable() {
        return isAvailable;
    }
}
