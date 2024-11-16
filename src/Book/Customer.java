/**
 * Represents a customer who can book movie tickets and maintain a record of their transactions.
 */
package Book;

import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    /**
     * The unique ID of the customer.
     */
    private final int id;

    /**
     * The static counter used to generate unique IDs for customers.
     */
    private static int lastID = 0;

    /**
     * The name of the customer.
     */
    private String name;

    /**
     * The age of the customer.
     */
    private short age;

    /**
     * A list of receipts representing the customer's booking history.
     */
    private List<Receipt> receipts;

    /**
     * Constructs a new customer with the specified name and age.
     *
     * @param name the name of the customer
     * @param age  the age of the customer
     */
    public Customer(String name, Short age) {
        setName(name);
        setAge(age);
        receipts = new ArrayList<Receipt>();
        id = lastID++;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the age of the customer.
     *
     * @return the age of the customer
     */
    public short getAge() {
        return age;
    }

    /**
     * Retrieves the list of receipts for the customer.
     *
     * @return a list of the customer's receipts
     */
    public List<Receipt> getReceipts() {
        return receipts;
    }

    /**
     * Retrieves the unique ID of the customer.
     *
     * @return the customer's ID
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the name of the customer. This method is private and can only be used within the class.
     *
     * @param name the name to be set for the customer
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the age of the customer. This method is private and can only be used within the class.
     * Additional validation for age requirements should be implemented here.
     *
     * @param age the age to be set for the customer
     */
    private void setAge(short age) {
        // TODO: Add validation for age (e.g., must be 18 or older).
        this.age = age;
    }

    /**
     * Books a movie for the customer and generates a receipt.
     *
     * @param screenTime the screen time of the movie
     * @param movie      the movie to be booked
     * @param seats      the list of seats to be reserved
     * @return the receipt for the booking if successful, otherwise null
     */
    public Receipt bookMovie(ScreenTime screenTime, Movie movie, List<Hall.Seat> seats) {
        Receipt receipt = Order.bookMovieTime(this, screenTime, movie, seats);
        if (receipt != null) {
            receipts.add(receipt);
            return receipt;
        }
        return null;
    }
}
