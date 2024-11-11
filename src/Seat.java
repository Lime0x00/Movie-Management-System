

package com.project.project;


import static com.project.project.enClass.FirstClass;
import static com.project.project.enClass.SecondClass;
import static com.project.project.enClass.ThirdClass;



/*
    The Seat class represents a seat in a movie hall with attributes ID, availability,
    price, and class type. The price is determined based on the class type of the seat.
 */
public class Seat {

    // Unique identifier for the seat
    private String id;
    
    // Indicates whether the seat is available for booking
    private boolean isAvailable;
    
    // Price of the seat based on its class type
    private float price;
    
    // Class type of the seat (FirstClass, SecondClass, ThirdClass)
    private enClass classType;

    /*
      Sets the unique identifier for the seat.
     */
    public void setId(String id) {
        this.id = id;
    }

    /*
       Sets the class type for the seat. The class type affects the price of the seat.
     */
    public void setClassType(enClass classType) {
        this.classType = classType;
    }

    /* Sets the availability of the seat for booking. */
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /*
      Sets the price of the seat based on its class type. The price varies by class:
      - FirstClass: 200.50
      - SecondClass: 250.95
      - ThirdClass: 350.00
     */
    private float setPrice() {
        switch (classType) {
            case FirstClass -> price = 200.50F;
            case SecondClass -> price = 250.95F;
            case ThirdClass -> price = 350.00F;
            default -> throw new AssertionError("Seat Class is Unknown.");
        }
        return price;
    }

    /* Gets the unique identifier for the seat */
    public String getId() {
        return id;
    }

    /* Gets the class type of the seat */
    public enClass getClassType() {
        return classType;
    }

    /*
      Gets the price of the seat. This method calls setPrice() to determine the price
      based on the class type
    */
    public float getPrice() {
        return setPrice();
    }

    /* Checks if the seat is available for booking */
    public boolean isIsAvailable() {
        return isAvailable;
    }
}
