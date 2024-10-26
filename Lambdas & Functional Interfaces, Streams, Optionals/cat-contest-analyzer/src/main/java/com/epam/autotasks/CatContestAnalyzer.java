package com.epam.autotasks;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class CatContestAnalyzer {

    public static final Integer DEFAULT_VALUE = -1;

    public Integer getMaxResult(List<Cat> cats) {
        return cats.stream()
                   .map(cat -> cat.getContestResult().getSum())
                   .max(Integer::compareTo)
                   .orElse(DEFAULT_VALUE);
    }

    public Integer getMinResult(List<Cat> cats) {
        return cats.stream()
                   .map(cat -> cat.getContestResult().getSum())
                   .filter(result -> result > 0)
                   .min(Integer::compareTo)
                   .orElse(DEFAULT_VALUE);
    }

    public OptionalDouble getAverageResultByBreed(List<Cat> cats, Cat.Breed breed) {
        return cats.stream()
                   .filter(cat -> cat.getBreed() == breed)
                   .mapToInt(cat -> cat.getContestResult().getSum())
                   .average();
    }

    public Optional<Cat> getWinner(List<Cat> cats) {
        return cats.stream()
                   .max((cat1, cat2) -> Integer.compare(cat1.getContestResult().getSum(), cat2.getContestResult().getSum()));
    }

    public List<Cat> getThreeLeaders(List<Cat> cats) {
        return cats.stream()
                .filter(cat -> cat.getContestResult() != null && cat.getContestResult().getSum() != null)
                .sorted((cat1, cat2) -> Integer.compare(cat2.getContestResult().getSum(), cat1.getContestResult().getSum()))
                .limit(3)
                .collect(Collectors.toList());
    }
    

    public boolean validateResultSumNotNull(List<Cat> cats) {
        return cats.stream()
                   .allMatch(cat -> cat.getContestResult().getSum() > 0);
    }

    public boolean validateAllResultsSet(List<Cat> cats) {
        return cats.stream()
                   .noneMatch(cat -> cat.getContestResult().getSum() == 0);
    }

    public Optional<Cat> findAnyWithAboveAverageResultByBreed(List<Cat> cats, Cat.Breed breed) {
        double average = cats.stream()
                             .filter(cat -> cat.getBreed() == breed)
                             .mapToInt(cat -> cat.getContestResult().getSum())
                             .average()
                             .orElse(0.0);

        return cats.stream()
                   .filter(cat -> cat.getBreed() == breed && cat.getContestResult().getSum() > average)
                   .findAny();
    }
}
