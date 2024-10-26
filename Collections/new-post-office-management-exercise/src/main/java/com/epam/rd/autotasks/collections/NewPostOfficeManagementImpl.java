package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NewPostOfficeManagementImpl implements NewPostOfficeManagement {
    private final List<Box> parcels;

    public NewPostOfficeManagementImpl(List<Box> parcels) {
        if (parcels == null || parcels.contains(null)) {
            throw new NullPointerException("List of parcels must not be null and should not contain null values.");
        }
        this.parcels = new ArrayList<>(parcels);
    }

    @Override
    public Optional<Box> getBoxById(int id) {
        // Binary search to find the box by ID, assuming the list is sorted by ID
        int low = 0;
        int high = parcels.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Box midBox = parcels.get(mid);

            if (midBox.getId() == id) {
                return Optional.of(midBox);
            } else if (midBox.getId() < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Box> getBoxesByRecipient(String recipient) {
        if (recipient == null) {
            throw new NullPointerException("Recipient must not be null.");
        }

        List<Box> result = new ArrayList<>();
        for (Box box : parcels) {
            if (box.getRecipient().equals(recipient)) {
                result.add(box);
            }
        }

        return result;
    }

    @Override
    public String getDescSortedBoxesByWeight() {
        List<Box> sortedBoxes = new ArrayList<>(parcels);
        sortedBoxes.sort(Comparator.comparingDouble(Box::getWeight).reversed());

        StringBuilder sb = new StringBuilder();
        for (Box box : sortedBoxes) {
            sb.append(box.getId()).append(";");
        }

        return sb.toString().replace(";", "\n");
    }

    @Override
    public String getAscSortedBoxesByCost() {
        List<Box> sortedBoxes = new ArrayList<>(parcels);
        sortedBoxes.sort(Comparator.comparing(Box::getCost));

        StringBuilder sb = new StringBuilder();
        for (Box box : sortedBoxes) {
            sb.append(box.getId()).append(";");
        }

        return sb.toString().replace(";", "\n");
    }

    @Override
    public BigDecimal getAverageWeight() {
        if (parcels.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal totalWeight = BigDecimal.ZERO;
        for (Box box : parcels) {
            totalWeight = totalWeight.add(BigDecimal.valueOf(box.getWeight()));
        }

        return totalWeight.divide(BigDecimal.valueOf(parcels.size()), RoundingMode.HALF_UP);
    }
}
