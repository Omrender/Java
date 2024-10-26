package com.epam.autotasks;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CatContestHelper {

    public static final Integer CARRIER_THRESHOLD = 30;

    // Method to calculate the number of carriers needed
    public Integer getCarrierNumber(List<Cat> cats) {
        double totalWeight = 0.0;
    
        for (Cat cat : cats) {
            if (cat != null && cat.getWeight() != null) {
                double weight = cat.getWeight();
                if (weight > 0 && weight <= 0.5) {
                    weight = 1.0; // Count as 1 kg if weight is between 0 and 0.5 kg
                }
                totalWeight += weight;
            }
        }
    
        // Calculate number of carriers needed and round up
        return (int) Math.ceil(totalWeight / CARRIER_THRESHOLD);
    }
    

    // Method to generate the carrier ID
    public String getCarrierId(List<Cat> cats) {
        StringBuilder idBuilder = new StringBuilder("CF");
    
        for (Cat cat : cats) {
            if (cat != null) {
                String name = cat.getName() != null ? cat.getName().toUpperCase() : "";
                String breed = cat.getBreed() != null ? cat.getBreed().name().toUpperCase() : "";
    
                // Append the first three letters of the cat's name and breed
                idBuilder.append(name.length() >= 3 ? name.substring(0, 3) : name);
                idBuilder.append(breed.length() >= 3 ? breed.substring(0, 3) : breed);
            }
        }
    
        return idBuilder.toString();
    }
    

    // Method to count the total number of awards
    public Integer countTeamAwards(List<Cat> cats) {
        int totalAwards = 0;
        
        for (Cat cat : cats) {
            if (cat != null && cat.getAwards() != null) {
                totalAwards += cat.getAwards();
            }
        }
        
        return totalAwards;
    }
}
