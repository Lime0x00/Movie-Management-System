import java.util.List;

public class User {
    private int id;
    private String name;
    private short age;
    private List<Receipt> receipts;
    
    
    User (String name, String email, String password, Short age) {
        //todo: Change ID as static
        /*
        USE METHODS TO SET TO ADD SOME PROTECTION
        *setName();
        *setAge();
        * */
    }
    
    // Helper Methods
    
    private void setName (String name) {
        //Some Checks here
        this.name = name;
    }
    

    private void setAge (short age) {
        //todo: Some Requirements to age here for example (From Age 18 - ...)
        this.age = age;
    }
    
    
    
    public boolean bookScreenTime(ScreenTime screenTime, Movie movie, List<Seat> seats) {
        Receipt receipt = Order.bookScreenTime(id, screenTime, movie, seats);
        if (receipt != null) {
            receipts.add(receipt);
            return true;
        } 
        return false;
    }
    
    
    
    
    // Getters Methods here Name, Age, ...
    //todo: Add Methods for getters
    public List<Receipt> getReceipts () {
        return receipts;
    }
}
