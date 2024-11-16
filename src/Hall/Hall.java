package Hall;

import static Hall.enClass.FIRST;
import static Hall.enClass.SECOND;
import static Hall.enClass.THIRD;

/**
 * Represents a movie theater hall with rows and columns of seats. 
 * Each seat has an ID, availability, price, and class type. 
 * The class also handles the booking and management of seats.
 */
public class Hall {
    private byte id; // Unique identifier for the hall
    private int numberOfRows; // Number of rows in the hall
    private int numberOfCols; // Number of columns in the hall
    private int bookedSeatsCount; // Counter for booked seats
    private Seat[][] seats; // 2D array representing the seats in the hall

    /**
     * Constructor to initialize a Hall object with the given ID, number of rows, and columns.
     *
     * @param id          The unique identifier for the hall. Must be between 1 and 126.
     * @param numberOfRows The number of rows in the hall. Must be at least 1 and at most 26 (A-Z).
     * @param numberOfCols The number of columns in the hall. Must be at least 1.
     */
    public Hall(byte id, int numberOfRows, int numberOfCols) {
        setID(id);
        setRows(numberOfRows);
        setCols(numberOfCols);
        initializeSeats(numberOfRows, numberOfCols);
    }

    /**
     * Copy constructor to create a new Hall object from an existing one.
     *
     * @param other The existing Hall object to copy.
     */
    public Hall(Hall other) {
        setID(other.id);
        setRows(other.numberOfRows);
        setCols(other.numberOfCols);
        initializeSeats(other.numberOfRows, other.numberOfCols);
    }

    // Setter Methods

    /**
     * Sets the ID of the hall. Must be between 1 and 126.
     *
     * @param id The unique identifier for the hall.
     */
    public void setID(byte id) {
        if (id > 0 && id < 127) {
            this.id = id;
        } else {
            System.out.println("Error: Invalid ID - must be between 1 and 126.");
        }
    }

    /**
     * Sets the seat ID based on its position in the row and column.
     *
     * @param row The row index of the seat.
     * @param col The column index of the seat.
     */
    private void setSeatID(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfCols) {
            System.out.println("Error: Invalid seat position - row or column out of bounds.");
        } else {
            String seatID = "" + (char) ('A' + row) + (col + 1);
            seats[row][col].setID(seatID);
        }
    }

    /**
     * Sets the number of rows in the hall. Must be at least 1 and at most 26.
     *
     * @param numberOfRows The number of rows in the hall.
     */
    private void setRows(int numberOfRows) {
        if (numberOfRows >= 1) {
            this.numberOfRows = Math.min(numberOfRows, 26);
        } else {
            System.out.println("Error: Number of rows must be at least 1.");
        }
    }

    /**
     * Sets the number of columns in the hall. Must be at least 1.
     *
     * @param numberOfCols The number of columns in the hall.
     */
    private void setCols(int numberOfCols) {
        if (numberOfCols >= 1) {
            this.numberOfCols = numberOfCols;
        } else {
            System.out.println("Error: Number of columns must be at least 1.");
        }
    }

    /**
     * Sets the class of a seat based on its row position.
     *
     * @param row The row index of the seat.
     * @param col The column index of the seat.
     */
    private void setSeatClass(int row, int col) {
        int firstClassLimit = (int) Math.ceil(0.2 * numberOfRows); // Top 20% rows are first class
        int secondClassLimit = firstClassLimit + (int) Math.ceil(0.5 * numberOfRows); // Next 50% are second class

        if (row < firstClassLimit) {
            seats[row][col].setClass(FIRST);
        } else if (row < secondClassLimit) {
            seats[row][col].setClass(SECOND);
        } else {
            seats[row][col].setClass(THIRD);
        }
    }

    // Getter Methods

    /**
     * Gets the total number of seats in the hall.
     *
     * @return The total number of seats.
     */
    public int getTotalSeats() {
        return numberOfRows * numberOfCols;
    }

    /**
     * Gets the total number of booked seats in the hall.
     *
     * @return The number of booked seats.
     */
    public int getBookedSeats() {
        return bookedSeatsCount;
    }

    /**
     * Gets the ID of the hall.
     *
     * @return The hall ID.
     */
    public int getID() {
        return id;
    }

    /**
     * Gets the 2D array of seats in the hall.
     *
     * @return The seat array.
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /**
     * Gets the number of rows in the hall.
     *
     * @return The number of rows.
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Gets the number of columns in the hall.
     *
     * @return The number of columns.
     */
    public int getNumberOfCols() {
        return numberOfCols;
    }

    // Other Methods

    /**
     * Initializes the seats in the hall with IDs and class types.
     *
     * @param numberOfRows The number of rows in the hall.
     * @param numberOfCols The number of columns in the hall.
     */
    private void initializeSeats(int numberOfRows, int numberOfCols) {
        seats = new Seat[numberOfRows][numberOfCols];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCols; col++) {
                seats[row][col] = new Seat();
                setSeatID(row, col);
                setSeatClass(row, col);
            }
        }
    }

    /**
     * Books a seat in the hall based on its ID.
     *
     * @param seatID The unique ID of the seat to book.
     * @return True if the seat was successfully booked; false otherwise.
     */
    public boolean bookSeat(String seatID) {
        int row = seatID.charAt(0) - 'A';
        int col = Integer.parseInt(seatID.substring(1)) - 1;

        if (row >= 0 && row < numberOfRows && col >= 0 && col < numberOfCols) {
            if (seats[row][col].isAvailable()) {
                seats[row][col].setAvailability(false);
                bookedSeatsCount++;
                return true;
            } else {
                System.out.println("Error: Seat " + seatID + " is already booked.");
            }
        } else {
            System.out.println("Error: Seat " + seatID + " is out of bounds.");
        }
        return false;
    }

    /**
     * Checks if the hall is fully booked.
     *
     * @return True if all seats are booked; false otherwise.
     */
    public boolean isFull() {
        return getBookedSeats() == getTotalSeats();
    }

    /**
     * Represents a seat in the hall with attributes like ID, availability, price, and class type.
     */
    public static class Seat {
        private String id; // Unique identifier for the seat
        private boolean isAvailable = true; // Seat availability
        private float price; // Price of the seat
        private enClass classType; // Class type of the seat (FirstClass, SecondClass, ThirdClass)

        // Setters and Getters with Documentation...
    }
}
