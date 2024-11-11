
package com.project.project;

import java.util.*;


public class Report {

    public int numberOfSoldSeats(Movie movie){
        return movie.getBookedSeats();
    }
    
   public List<Date[]> getCrowdedTimes() {
        List<Date[]> mostCrowdedTimes = new ArrayList<>();  // List to store all equally crowded time periods
        int max = 0;                                        // Variable to store the highest number of booked seats found
        int tempMax=0;
        // Loop through all movies in the library
        for (Movie movie : MovieLibrary.getMovies()) {
            
            // Loop through all screening times for each movie
            for (ScreenTime screenTime : movie.getScreenTimes()) {
                
                // Count the number of booked seats for this specific screen time
                tempMax = getNumberOfSeats(screenTime.getStartDate(), screenTime.getEndDate());

                // Check if this screening has more booked seats than the current maximum
                if (tempMax > max) {
                    
                    // Found a new maximum, so clear the list and add this time period
                    mostCrowdedTimes.clear();
                    max = tempMax;     // Update max with the new highest number of booked seats     
                    
                    mostCrowdedTimes.add(new Date[] { screenTime.getStartDate(), screenTime.getEndDate() });   //add the new max into the Date list
                    
                } else if (tempMax == max) {        // If this screening has the same number of booked seats as the max
                    
                    mostCrowdedTimes.add(new Date[] { screenTime.getStartDate(), screenTime.getEndDate() });    //add the similar time to the Date list
                }
            }
        }

        return mostCrowdedTimes;  // Return the list of crowded time periods with the maximum booked seats
    }                                                    

    
     /* Calculates the total number of booked seats for a specified screening period across all movies. */
     
    private int getNumberOfSeats(Date startDate, Date endDate) {                                            
        int sumBookedSeats = 0;  // Variable to accumulate the total booked seats for the period

        // Loop through all movies in the library
        for (Movie movie : MovieLibrary.getMovies()) {
            // Loop through each screening time for the current movie
            for (ScreenTime screenTime : movie.getScreenTimes()) {
                // Check if the screening time matches the specified start and end dates
                
                if (screenTime.getStartDate().equals(startDate) && screenTime.getEndDate().equals(endDate)) {
                    sumBookedSeats += screenTime.getBookedSeats();  // Add booked seats to the total
                }
                
            }
        }
        
        return sumBookedSeats;  // Return the total number of booked seats for the period
    }

}

