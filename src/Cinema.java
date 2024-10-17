import java.util.List;


/* Class Can be deleted */
public class Cinema {
    private List<Hall> halls;
    
    
    public boolean addHall (Hall hall) {
        // Some Checks if there is max limit of Halls
        halls.add(hall);
        return true;
    }
    
}
