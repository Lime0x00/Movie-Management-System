import java.util.Date;

public class ScreenTime {
    private Hall hall;
    private Date startDate;
    private Date endDate;
    //private static float breakDuration;
    
    
    ScreenTime (Hall hall, Date startDate /*long break_time*/) {
        this.hall = hall;
        setStartDate(startDate);
        setEndDate();
    }
    
    public int getBookedSeats () {
        return hall.getBookedSeats();
    }
    
    
    public void setStartDate (Date startDate) {
        //Some Checks Here
        this.startDate = startDate;
    }
    private void setEndDate() {
        //Some Checks Here
        endDate.setTime(startDate.getTime() + hall.getDuration()  /*break_time*/);
    }
    
    public Date getStartDate () {
        return startDate;
    }
    
    public Date getEndDate () {
        return endDate;
    }
    
    public Hall getHall () {
        return hall;
    }
}
