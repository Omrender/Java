package com.epam.autotasks;

import java.util.ArrayList;
import java.util.List;

public class CatDatabase {

    // Returns a list of cat names for a specific breed
    public List<String> getCatNamesByBreed(List<Cat> cats, Cat.Breed breed) {
        List<String> catNames = new ArrayList<>();
        if (cats == null || breed == null) {
            return catNames;
        }
        for (Cat cat : cats) {
            if (cat != null && breed.equals(cat.getBreed()) && cat.getName() != null) {
                catNames.add(cat.getName());
            }
        }
        return catNames;
    }

    // Returns a list of cats who are age years old or younger
    public List<Cat> filterYoungerCatsByAge(List<Cat> cats, Integer age) {
        List<Cat> youngerCats = new ArrayList<>();
        if (cats == null || age == null) {
            return youngerCats;
        }
        for (Cat cat : cats) {
            if (cat != null && cat.getAge() != null && cat.getAge() <= age) {
                youngerCats.add(cat);
            }
        }
        return youngerCats;
    }

    // Returns a list of cats whose name starts with the specified prefix (case-insensitive)
    public List<Cat> filterCatsByNamePrefix(List<Cat> cats, String prefix) {
        List<Cat> filteredCats = new ArrayList<>();
        if (cats == null || prefix == null) {
            return filteredCats;
        }
        for (Cat cat : cats) {
            if (cat != null && cat.getName() != null && cat.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                filteredCats.add(cat);
            }
        }
        return filteredCats;
    }
}
