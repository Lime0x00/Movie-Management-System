package Movie;

import java.util.List;

public class MovieLibrary {
    private static List<Movie> movies;

    public MovieLibrary(List<Movie> movies) {
        MovieLibrary.movies = movies;
    }

    public static boolean addMovie(Movie movie) {
        movies.add(movie);
        System.out.println(movie.getTitle() + " has been Added Successfully.");
        return true;
    }

    public boolean deleteMovie (Movie movie) {
        if(hasMovie(movie)){
            System.out.println("Are you sure you want delete "+ movie.getTitle() + " movie ?"+" Y/N" );
            String input = scanner.nextLine().trim().toLowerCase();
            if(input.equals("y")){
                movies.remove(movie);
                System.out.println(movie.getTitle() + " has been deleted Successfully.");
                return true;
            }else{
                System.out.println("Error: Deletion canceled.");
                return false;
            }
        }else{
            System.err.println("Error: Movie not found.");
            return false;
        }
    }

    public static boolean hasMovie(Movie movie) {
        if (movies.contains(movie)){
            return true;
        }else{
            System.out.println("Error: "+movie.getTitle()+" not found.");
            return false;
        }
    }

    public static List<Movie> getMovies () {
        return movies;
    }
}