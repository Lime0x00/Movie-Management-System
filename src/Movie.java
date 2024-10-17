import java.util.Date;
import java.util.List;

public class Movie {
    
    private enum enAgeRating {
        PG_13("Parental Guide 13", 13);
        
        private final String description;
        private final int age;

        enAgeRating (String description, int age) {
            this.description = description;
            this.age = age;
        }

        public String getDescription() {
            return description;
        }
        
        public int getAge () {
            return this.age;
        }
    };
    private enum enGenre {
        ACTION ("Action"),
        COMEDY ("Comedy"),
        
        ROMANCE ("Romance");
        
        private final String description;
        
        enGenre (String description) {
            this.description = description;
        }
        
        public String getDescription () {
            return this.description;
        }
    };
    private static int id;
    
    private String title;
    
    private float duration;
    private List<ScreenTime> screenTimes;
    
    private int bookedSeats;

    
    Movie (String title) {
        
    }
    
    
    public static int getID () {
        return id;
    }

    public float getDuration() {
        return duration;
    }
    
    
    public boolean addScreenTime (ScreenTime screenTime) {
        // Some Checks to see this is valid or not 
        screenTimes.add(screenTime);
        return true; // Just A placeholder
    }
    
    public List<ScreenTime> getScreenTimes () {
        return screenTimes;
    }
    
    
    public boolean hasScreenTime (ScreenTime screenTime) {
        return this.screenTimes.contains(screenTime);
    }
    
     
    
    public int getBookedSeats () {
        int bookedSeats = 0;
        for (ScreenTime screenTime : screenTimes) {
            bookedSeats += screenTime.getBookedSeats();
        }
        return bookedSeats;
    }
}
