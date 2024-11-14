import java.util.List;

public class Movie {
    private int id;
    private static int lastID = 0;
    private String title;
    private enGenre genre;
    private float duration;
    private List<ScreenTime> lsScreenTimes;

    
    Movie (String title, enGenre genre, float duration, List<ScreenTime> screenTimes) {
        setID();
        setTitle(title);
        setGenre(genre);
        setDuration(duration);
        // lsScreenTimes = screenTimes;
        setScreenTimes(screenTimes);
    }

    // setter functions
    private void setID () {id = lastID++;}
    private void setTitle (String title) {this.title = title;}
    private void setGenre (enGenre genre) {this.genre = genre;}
    // setDuration and setScreenTimes methods Not Included In UML but May be helpful
    private void setDuration (float duration) {this.duration = duration;}
    private void setScreenTimes (List<ScreenTime> screenTimes){this.lsScreenTimes = screenTimes;}

    // getter functions
    public int getID () {return id;}
    public String getTitle () {return title;} 
    public String getGenre () {return genre.getGenre();}
    public float getDuration() {return duration;}
    public List<ScreenTime> getScreenTimes () {return lsScreenTimes;}
    public int getBookedSeats () {
        int bookedSeats = 0 ;
        for (ScreenTime screenTime : lsScreenTimes) bookedSeats += screenTime.getBookedSeats();
        return bookedSeats;
    }
    
    public boolean addScreenTime (ScreenTime screenTime) {
        // Some Checks to see this is valid or not 
        // In Case There is a schedule to hall
        if(screenTime == null){
            System.out.println("Error: Invalid ScreenTime. It Can't be Null !");
            return false;
        }
        else if(lsScreenTimes.contains(screenTime)){
            System.out.println("Error: ScreenTime Already Exist. It Can't be Duplicated !");
            return false;
        }
        lsScreenTimes.add(screenTime);
        System.out.println("ScreenTime Added Successfully.");
        return true; // Just A placeholder   
    }
    
    public boolean hasScreenTime (ScreenTime screenTime) {
        if(lsScreenTimes.contains(screenTime)){
            return true;
        }else{
            System.out.println("Error: This ScreenTime not found.");
            return false;
        }    
    }
}
