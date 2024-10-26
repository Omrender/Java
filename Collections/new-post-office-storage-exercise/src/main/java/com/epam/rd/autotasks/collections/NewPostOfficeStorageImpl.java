package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class NewPostOfficeStorageImpl implements NewPostOfficeStorage {
    // Ensure parcels is always initialized
    private List<Box> parcels = new LinkedList<>();

    /**
     * Creates internal storage for parcels.
     */
    public NewPostOfficeStorageImpl() {
        // Ensure parcels is initialized, though it's already initialized during declaration
    }

    /**
     * Creates internal storage and appends all parcels from the collection.
     * It must add either all the parcels or nothing, if an exception occurs.
     *
     * @param boxes a collection of parcels.
     * @throws NullPointerException if the parameter is {@code null}
     *                              or contains {@code null} values.
     */
    public NewPostOfficeStorageImpl(Collection<Box> boxes) {
        if (boxes == null || boxes.contains(null)) {
            throw new NullPointerException("The collection or its elements cannot be null.");
        }
        parcels = new LinkedList<>(boxes); // Copy the collection to the internal list
    }

    @Override
    public boolean acceptBox(Box box) {
        if (box == null) {
            throw new NullPointerException("Box cannot be null.");
        }
        return parcels.add(box);
    }

    @Override
    public boolean acceptAllBoxes(Collection<Box> boxes) {
        if (boxes == null || boxes.contains(null)) {
            throw new NullPointerException("The collection or its elements cannot be null.");
        }
        try {
            return parcels.addAll(boxes);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean carryOutBoxes(Collection<Box> boxes) {
        if (boxes == null || boxes.contains(null)) {
            throw new NullPointerException("The collection or its elements cannot be null.");
        }
        return parcels.removeAll(boxes);
    }

    @Override
    public List<Box> carryOutBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null.");
        }

        List<Box> removedBoxes = new LinkedList<>();
        Iterator<Box> iterator = parcels.iterator();

        while (iterator.hasNext()) {
            Box box = iterator.next();
            if (predicate.test(box)) {
                removedBoxes.add(box);
                iterator.remove();
            }
        }
        return removedBoxes;
    }

    @Override
    public List<Box> getAllWeightLessThan(double weight) {
        List<Box> result = new LinkedList<>();
        for (Box box : parcels) {
            if (box.getWeight() < weight) {
                result.add(box);
            }
        }
        return result;
    }

    @Override
    public List<Box> getAllCostGreaterThan(BigDecimal cost) {
        List<Box> result = new LinkedList<>();
        for (Box box : parcels) {
            if (box.getCost().compareTo(cost) > 0) {
                result.add(box);
            }
        }
        return result;
    }

    @Override
    public List<Box> getAllVolumeGreaterOrEqual(double volume) {
        List<Box> result = new LinkedList<>();
        for (Box box : parcels) {
            if (box.getVolume() >= volume) {
                result.add(box);
            }
        }
        return result;
    }

    @Override
    public List<Box> searchBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null.");
        }

        List<Box> result = new LinkedList<>();
        for (Box box : parcels) {
            if (predicate.test(box)) {
                result.add(box);
            }
        }
        return result;
    }

    @Override
    public void updateOfficeNumber(Predicate<Box> predicate, int newOfficeNumber) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null.");
        }

        // Update the office number of each box that satisfies the predicate
        ListIterator<Box> iterator = parcels.listIterator();
        while (iterator.hasNext()) {
            Box currentBox = iterator.next();
            if (predicate.test(currentBox)) {
                try {
                    // Clone the current box and update its office number
                    Box updatedBox = currentBox.clone();
                    updatedBox.setOfficeNumber(newOfficeNumber);
                    
                    // Replace the old box with the updated one
                    iterator.set(updatedBox);
                } catch (CloneNotSupportedException e) {
                    // Handle the clone exception if cloning is not supported
                    throw new RuntimeException("Failed to clone the box object", e);
                }
            }
        }
    }
}
