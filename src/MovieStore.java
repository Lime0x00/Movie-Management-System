import java.util.List;

public class MovieStore {
    private static List<Movie> movies;
    
    public static boolean hasMovie (Movie movie) {
        return movies.contains(movie);
    } 
    
    public static List<Movie> getMovies () {
        return movies;
    }
    
    //todo: Add Methods to delete ...
}
