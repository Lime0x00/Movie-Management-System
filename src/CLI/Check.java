/**
 * Utility class for performing various checks and validations related to the booking system.
 */
package CLI;

import Book.Order;
import Hall.Hall;
import Movie.Movie;
import Movie.ScreenTime;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Check {

    /**
     * Enumeration representing possible responses to confirmation prompts.
     */
    public enum enResponse {
        YES,
        NO,
        UNKNOWN,
    }

    /**
     * Checks if the given choice is a valid index in the provided array.
     *
     * @param choice the user's choice (1-based index)
     * @param arr    the array of options
     * @param <T>    the type of elements in the array
     * @return {@code true} if the choice is valid, {@code false} otherwise
     */
    public static <T> boolean isValidAnswer(int choice, T[] arr) {
        return choice > 0 && choice <= arr.length;
    }

    /**
     * Checks if the given choice is a valid index in the provided list.
     *
     * @param choice the user's choice (1-based index)
     * @param list   the list of options
     * @param <T>    the type of elements in the list
     * @return {@code true} if the choice is valid, {@code false} otherwise
     */
    public static <T> boolean isValidAnswer(int choice, List<T> list) {
        return choice > 0 && choice <= list.size();
    }

    /
