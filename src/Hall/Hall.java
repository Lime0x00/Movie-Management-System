package Hall;

public class Hall {
    private byte id;
    private int numberOfRows;
    private int numberOfCols;
    private int bookedSeatsCount;
    private Seat[][] seats;
    
    public Hall(byte id, int numberOfRows, int numberOfCols) {
        setID(id);
        setRows(numberOfRows);
        setCols(numberOfCols);
        initializeSeats(numberOfRows, numberOfCols);
    }
    
    
    // setter functions
    public void setID (byte id) {
        if (id > 0 && id < 127) {
            this.id = id;
        }else{
            System.out.println("Error: Invalid ID - must be between 1 and 126.");
        }
    }
    
    private void setSeatID(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfCols) {
            System.out.println("Error: Invalid seat position - row or column out of bounds.");
        }else{
            String seatID;
            char rowLetter = (char) ('A' + row);
            int colIdx = col + 1;
            seatID = "" + rowLetter + colIdx;
            seats[row][col].setID(seatID);
        }
    }

    private void setRows (int numberOfRows) {
        //Number of English Characters
        if (numberOfRows >= 1) {
            this.numberOfRows = Math.min(numberOfRows, 26);
        }else{
            System.out.println("Error: Number of rows must be at least 1.");
        }
        
    }
    
    private void setCols (int numberOfCols) {
        if (numberOfCols >= 1) {
            this.numberOfCols = numberOfCols;
        }else{
            System.out.println("Error: Number of columns must be at least 1.");
        }
    }
    
    private void setSeatClass(int row, int col) {
        int firstClassLimit = (int) Math.ceil(0.2 * numberOfRows); // 20% to first class
        int secondClassLimit = firstClassLimit + (int) Math.ceil(0.5 * numberOfRows); // 50% to second class
        // the rest to third class
        if (row >= 0 && row < firstClassLimit) {
            seats[row][col].setClass(enClass.FIRST);
        } else if (row >= firstClassLimit && row < secondClassLimit) {
            seats[row][col].setClass(enClass.SECOND);
        } else {
            seats[row][col].setClass(enClass.THIRD);
        }
    }
    
    // getter functions
    public int getTotalSeats () {return numberOfRows * numberOfCols;}
    public int getBookedSeats () {return bookedSeatsCount;}
    public int getID () {return id;}
    //not in UML
    public Seat[][] getSeats () {return seats;}
    public int getNumberOfRows () {return numberOfRows;}
    public int getNumberOfCols () {return numberOfCols;}
    
    private void initializeSeats(int numberOfRows, int numberOfCols) {
        seats = new Seat[numberOfRows][numberOfCols];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCols; col++) {
                seats[row][col] = new Seat();
                setSeatID(row, col);
                setSeatClass(row, col);
            }
        }
    }

    public boolean bookSeat (String seatID) {
        int row = seatID.charAt(0) - 'A';
        int col = Integer.parseInt(seatID.substring(1)) - 1;

        if ((row >= 0 && row < numberOfRows) && (col >= 0 && col < numberOfCols)) {
            if (seats[row][col].isAvailable()) {
                seats[row][col].setAvailability(false);
                bookedSeatsCount++;
                return true;
            }else{
                System.out.println("Error: Seat " + seatID + " is already booked.");
            }
        }else{
            System.out.println("Error: Seat " + seatID + " is out of bounds.");
        }
        return false;
    }
    
    public boolean isFull () {
        return getBookedSeats() == getTotalSeats();
    }    
}
