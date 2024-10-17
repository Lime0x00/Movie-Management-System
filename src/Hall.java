import java.util.Date;
import java.util.List;

public class Hall {
    private int id;
    private int numberOfRows;
    private int numberOfCols;
    private int bookedSeats;
    private Seat[][] seats;
    
    
    Hall (int id, Seat[][] seats) {
        setID(id);
        //setRows(seats.);
        //setCols(numberOfCols);
        intialzeSeats(seats);
    }
    
    Hall (int id, int numberOfRows, int numberOfCols) {
        setID(id);
        setRows(numberOfRows);
        setCols(numberOfCols);
        intialzeSeats(numberOfRows, numberOfCols);
    }
    
     
    public boolean bookSeat (String seatID) {
        if (seats[seatID.charAt(0)][seatID.charAt(1)].isAvailable()) {
            seats[seatID.charAt(0)][seatID.charAt(1)].setAvailablity(false);
            bookedSeats++;
            return true;
        } 
        return false;
    }
    
    
    //Helper Method
    public void setID (int id) {
        //Some Checks Here
        this.id = id;
    }

    private void setRows (int numberOfRows) {
        //Some Checks Here
        this.numberOfRows = numberOfRows;
    }
    
    private void setCols (int numberOfCols) {
        //Some Checks Here
        this.numberOfCols = numberOfCols;
    }
    private void intialzeSeats(int numberOfRows, int numberOfCols) {
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCols; col++) {
                setSeatID(row, col);
                setSeatClass(row, col);
            }
        }
    }

    private void intialzeSeats(Seat[][] seats) {
        this.seats = seats;
    }
    
    private void setSeatID(int row, int col) {
        // Can Write Any Way to Set ID
        StringBuilder seatID = new StringBuilder();
        char rowLetter = (char) ('A' + row);
        seatID.append(rowLetter);
        seatID.append(col + 1);
        seats[row][col].setID(seatID.toString());
    }
    
    private void setSeatClass (int row, int col) {
        char rowLetter = (char) ('A' + row);
        switch (rowLetter) {
            case 'A':
                seats[row][col].setClass(Seat.enClass.FIRST);
                break;
            case 'B':
                seats[row][col].setClass(Seat.enClass.SECOND);
                break;
            case 'C':
                seats[row][col].setClass(Seat.enClass.THIRD);
                break;
        }
    }
    
    
    public int getTotalSeats () {
        return seats.length;
    }
    
    public int getBookedSeats () {
        return bookedSeats;
    }
    
    
    public int getID () {
        return id;
    }
    
    public boolean isFull () {
        return getBookedSeats() == getTotalSeats();
    }
    
}
