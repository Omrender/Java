package com.epam.autotasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CatSelection {

    // Returns the first 'number' of cats from a sorted list based on the given comparator
    public List<Cat> getFirstNCatsSortedByComparator(List<Cat> cats, Comparator<Cat> comparator, int number) {
        if (cats == null || number < 0) throw new IllegalArgumentException("Invalid number of cats.");
        List<Cat> sortedCats = new ArrayList<>(cats);
        sortedCats.sort(comparator);
        return sortedCats.subList(0, Math.min(number, sortedCats.size()));
    }

    // Returns all cats except the first 'number' from a sorted list based on the given comparator
    public List<Cat> getWithoutFirstNCatsSortedByComparator(List<Cat> cats, Comparator<Cat> comparator, int number) {
        if (cats == null || number < 0) throw new IllegalArgumentException("Invalid number of cats.");
        List<Cat> sortedCats = new ArrayList<>(cats);
        sortedCats.sort(comparator);
        if (number >= sortedCats.size()) return new ArrayList<>();
        return sortedCats.subList(number, sortedCats.size());
    }

    // Returns a list of cats whose weight is smaller than the given threshold
    public List<Cat> getSmallCats(List<Cat> cats, int threshold) {
        if (cats == null || threshold <= 0 || threshold >= 999) throw new RuntimeException("Invalid weight threshold.");
        List<Cat> smallCats = new ArrayList<>();
        for (Cat cat : cats) {
            if (cat.getWeight() < threshold) {
                smallCats.add(cat);
            }
        }
        return smallCats;
    }

    // Returns a list of cats who are taller than the given threshold
    public List<Cat> getTallCats(List<Cat> cats, int threshold) {
        if (cats == null || threshold <= 0 || threshold >= 999) throw new RuntimeException("Invalid height threshold.");
        List<Cat> tallCats = new ArrayList<>();
        for (Cat cat : cats) {
            if (cat.getHeight() > threshold) {
                tallCats.add(cat);
            }
        }
        return tallCats;
    }

    // Returns a list of unique cat names
    public List<String> getUniqueNames(List<Cat> cats) {
        if (cats == null) throw new IllegalArgumentException("Cat list cannot be null.");
        Set<String> uniqueNames = new LinkedHashSet<>();
        for (Cat cat : cats) {
            uniqueNames.add(cat.getName());
        }
        return new ArrayList<>(uniqueNames);
    }
}
