package Movie;

import java.util.List;

public class Movie {
    private int id;
    private static int lastID = 0;
    private String title;
    private enGenre genre;
    private float duration;
    private List<ScreenTime> lsScreenTimes;

    
    public Movie(String title, enGenre genre, List<ScreenTime> screenTimes) {
        setID();
        setTitle(title);
        setGenre(genre);
        setScreenTimes(screenTimes);
    }

    private void setID () {id = lastID++;}
    private void setTitle (String title) {this.title = title;}
    private void setGenre (enGenre genre) {this.genre = genre;}
    // setDuration and setScreenTimes methods Not Included In UML but May be helpful
    private void setScreenTimes (List<ScreenTime> screenTimes){
        if(screenTimes.size() == 0) throw new IllegalArgumentException("ScreenTimes Can't be Empty !");
        this.lsScreenTimes = screenTimes;
        this.duration = screenTimes.get(0).getEndDate().getHours()-screenTimes.get(0).getStartDate().getHours();
    }

    public int getID () {return id;}
    public String getTitle () {return title;} 
    public String getGenre () {return genre.getGenre();}
    public float getDuration() {return duration;}
    public List<ScreenTime> getScreenTimes () {return lsScreenTimes;}
    public int getBookedSeats () {
        int bookedSeats = 0 ;
        for (ScreenTime screenTime : lsScreenTimes) bookedSeats += screenTime.getHall().getBookedSeats();
        return bookedSeats;
    }
    
    public boolean addScreenTime (ScreenTime screenTime) {
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
        return true;  
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
