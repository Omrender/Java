package com.epam.autotasks;

import java.util.*;
import java.util.stream.Collectors;

public class CatLibrary {

    public static final String EMPTY_STRING = "";

    // Maps cat names to Cat objects
    public Map<String, Cat> mapCatsByName(List<Cat> cats) {
        return cats.stream()
                   .filter(cat -> cat != null && cat.getName() != null)
                   .collect(Collectors.toMap(Cat::getName, cat -> cat));
    }

    // Maps cat breeds to a set of Cat objects
    public Map<Cat.Breed, Set<Cat>> mapCatsByBreed(List<Cat> cats) {
        return cats.stream()
                   .filter(cat -> cat != null && cat.getBreed() != null)
                   .collect(Collectors.groupingBy(
                           Cat::getBreed,
                           Collectors.toSet()
                   ));
    }

    // Maps breeds to a string of cat names, formatted as required
    public Map<Cat.Breed, String> mapCatNamesByBreed(List<Cat> cats) {
        return cats.stream()
                   .filter(cat -> cat != null && cat.getName() != null && !cat.getName().isEmpty())
                   .collect(Collectors.groupingBy(
                           Cat::getBreed,
                           Collectors.mapping(Cat::getName, 
                                              Collectors.joining(", ", "Cat names: ", "."))
                   ));
    }

    // Maps breeds to the average contest result
    public Map<Cat.Breed, Double> mapAverageResultByBreed(List<Cat> cats) {
        return cats.stream()
                   .filter(cat -> cat != null && cat.getContestResult() != null && cat.getBreed() != null)
                   .collect(Collectors.groupingBy(
                           Cat::getBreed,
                           Collectors.averagingDouble(cat -> {
                               ContestResult result = cat.getContestResult();
                               return result.getRunning() + result.getJumping() + result.getPurring();
                           })
                   ));
    }

    // Returns a sorted set of cats ordered by total contest results (from highest to lowest)
    public SortedSet<Cat> getOrderedCatsByContestResults(List<Cat> cats) {
        return cats.stream()
                   .filter(cat -> cat != null && cat.getContestResult() != null)
                   .collect(Collectors.toCollection(() -> 
                       new TreeSet<>(Comparator.comparingInt((Cat cat) -> {
                           ContestResult result = cat.getContestResult();
                           return result.getRunning() + result.getJumping() + result.getPurring();
                       }).reversed())
                   ));
    }
}
