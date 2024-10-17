public class Seat {
    enum enClass {
        FIRST("First Class"),
        SECOND("Second Class"),
        THIRD("Third Class");
        
        private final String description;
        
        enClass(String description) {
            this.description = description;
        }
        
        public String getDescription () {
            return this.description;
        }
    };
    private String id;
    private enClass class_type;
    private float price;
    private boolean isAvailable = false;
    
    


    public void setAvailablity(boolean available) {
        isAvailable = available;
    }
    
    
    public void setID (String id) {
        this.id = id;
    }
    
    public void setClass (enClass class_type) {
        this.class_type = class_type;
        setPrice();
    }
    
    private void setPrice () {
        switch (class_type) {
            case FIRST:
                price = 260.5f; // Is A PlaceHolder
                break;
            case SECOND:
                price = 200.5f; // Is A PlaceHolder
                break;
            case THIRD:
                price = 150.5f; // Is A PlaceHolder
                break;
        }
    }
    public float getPrice () {
        return this.price;
    }
    
    public String getID () {
        return id;
    }
    
    public String getClassName () { // Get Class Raise ERROR (Override Method in java)
        return class_type.description;
    }

    public boolean isAvailable () {
        return isAvailable;
    }
    
}
