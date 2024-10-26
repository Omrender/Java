package com.epam.rd.autotasks.collections;

import java.util.*;

public class BirthJournalManagementImpl implements BirthJournalManagement {

    private final Map<WeekDay, List<Baby>> journal;
    private boolean isImmutable;

    // Constructor initializes the journal map and sets immutability to false
    public BirthJournalManagementImpl() {
        this.journal = new EnumMap<>(WeekDay.class);
        for (WeekDay day : WeekDay.values()) {
            journal.put(day, new ArrayList<>());
        }
        this.isImmutable = false;
    }

    @Override
    public boolean addEntryOfBaby(WeekDay day, Baby baby) {
        if (isImmutable || baby == null || day == null) {
            return false;
        }
        List<Baby> babies = journal.get(day);
        if (babies == null) {
            return false;
        }
        babies.add(baby);
        return true;
    }

    @Override
    public void commit() {
        isImmutable = true;
    }

    @Override
    public int amountBabies() {
        int total = 0;
        for (List<Baby> babies : journal.values()) {
            total += babies.size();
        }
        return total;
    }

    @Override
    public List<Baby> findBabyWithHighestWeight(String gender) {
        List<Baby> result = new ArrayList<>();
        double maxWeight = Double.MIN_VALUE;
        
        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                if (baby.getGender().equals(gender)) {
                    if (baby.getWeight() > maxWeight) {
                        maxWeight = baby.getWeight();
                        result.clear();
                        result.add(baby);
                    } else if (baby.getWeight() == maxWeight) {
                        result.add(baby);
                    }
                }
            }
        }
        
        result.sort(Comparator.comparing(Baby::getName));
        return result;
    }

    @Override
    public List<Baby> findBabyWithSmallestHeight(String gender) {
        List<Baby> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        
        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                if (baby.getGender().equals(gender)) {
                    if (baby.getHeight() < minHeight) {
                        minHeight = baby.getHeight();
                        result.clear();
                        result.add(baby);
                    } else if (baby.getHeight() == minHeight) {
                        result.add(baby);
                    }
                }
            }
        }
        
        result.sort(Comparator.comparingDouble(Baby::getWeight));
        return result;
    }

    @Override
    public Set<Baby> findBabiesByBirthTime(String from, String to) {
        Set<Baby> result = new HashSet<>();
        String[] fromParts = from.split(":");
        String[] toParts = to.split(":");
        int fromMinutes = Integer.parseInt(fromParts[0]) * 60 + Integer.parseInt(fromParts[1]);
        int toMinutes = Integer.parseInt(toParts[0]) * 60 + Integer.parseInt(toParts[1]);
        
        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                String[] timeParts = baby.getTime().split(":");
                int babyMinutes = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
                if (babyMinutes >= fromMinutes && babyMinutes <= toMinutes) {
                    result.add(baby);
                }
            }
        }
        return result;
    }
}
