package com.epam.rd.autotasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class NewPostOffice {
    private final Collection<Box> listBox;
    private static final int COST_KILOGRAM = 5;
    private static final int COST_CUBIC_METER = 100;
    private static final double COEFFICIENT = 0.5;

    public NewPostOffice() {
        listBox = new ArrayList<>();
    }

    // Returns a shallow copy of the list of boxes
    public Collection<Box> getListBox() {
        return (Collection<Box>) ((ArrayList<Box>) listBox).clone();
    }

    // Calculates the cost of the box
    static BigDecimal calculateCostOfBox(double weight, double volume, int value) {
        BigDecimal costWeight = BigDecimal.valueOf(weight)
                .multiply(BigDecimal.valueOf(COST_KILOGRAM));
        BigDecimal costVolume = BigDecimal.valueOf(volume)
                .multiply(BigDecimal.valueOf(COST_CUBIC_METER));
        return costVolume.add(costWeight)
                .add(BigDecimal.valueOf(COEFFICIENT * value));
    }

    // Adds a box to the collection
    public boolean addBox(String addresser, String recipient, double weight, double volume, int value) {
        // Validate inputs
        if (addresser == null || recipient == null || weight < 0.5 || weight > 20.0 || 
            volume <= 0 || volume >= 0.25 || value <= 0) {
            throw new IllegalArgumentException("Invalid box details.");
        }
    
        // Calculate the shipping cost
        BigDecimal cost = calculateCostOfBox(weight, volume, value);
        
        // Create the box and add it to the list
        Box newBox = new Box(addresser, recipient, weight, volume, cost);
        return listBox.add(newBox);
    }
    

    // Delivers all boxes to a specific recipient and removes them from the list
    public Collection<Box> deliveryBoxToRecipient(String recipient) {
        Collection<Box> deliveredBoxes = new ArrayList<>();
        Iterator<Box> iterator = listBox.iterator();

        while (iterator.hasNext()) {
            Box box = iterator.next();
            if (box.getRecipient().equals(recipient)) {
                deliveredBoxes.add(box);
                iterator.remove(); // Remove the delivered box from the list
            }
        }

        return deliveredBoxes;
    }

    // Declines the cost of each box by the given percentage
    public void declineCostOfBox(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Invalid percentage.");
        }
    
        BigDecimal declineFactor = BigDecimal.valueOf(1 - percent / 100);
        
        // Apply the decline to each box's cost
        for (Box box : listBox) {
            BigDecimal originalCost = box.getShippingCost();
            BigDecimal declinedCost = originalCost.multiply(declineFactor);
            box.setShippingCost(declinedCost);
        }
    }
    
}
