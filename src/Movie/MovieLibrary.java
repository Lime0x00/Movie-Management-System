package Movie;

import java.util.List;

/**
 * Represents a library of movies with operations to add, delete, and query movies.
 * The library is managed as a static list shared across all instances.
 */
public class MovieLibrary {
    /** A static list containing all the movies in the library. */
    private static List<Movie> movies;

    /**
     * Constructs a MovieLibrary with the given list of movies.
     *
     * @param movies a list of movies to initialize the library.
     */
    public MovieLibrary(List<Movie> movies) {
        MovieLibrary.movies = movies;
    }

    /**
     * Adds a movie to the library.
     *
     * @param movie the movie to be added.
     * @return true if the movie was successfully added, false otherwise.
     */
    public static boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    /**
     * Deletes a movie from the library.
     *
     * @param movie the movie to be deleted.
     * @return true if the movie was successfully deleted, false otherwise.
     */
    public static boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
    }

    /**
     * Checks if the library contains a specific movie.
     *
     * @param movie the movie to check for.
     * @return true if the movie exists in the library, false otherwise.
     */
    public static boolean hasMovie(Movie movie) {
        return movies.contains(movie);
    }

    /**
     * Retrieves the list of all movies in the library.
     *
     * @return a list of movies in the library.
     */
    public static List<Movie> getMovies() {
        return movies;
    }
}
