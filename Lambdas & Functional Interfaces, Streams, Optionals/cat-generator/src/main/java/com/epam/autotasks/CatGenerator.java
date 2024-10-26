package com.epam.autotasks;

import java.util.ArrayList;
import java.util.List;

public class CatGenerator {

    public static class Cat {
        private final String name;
        private final Integer age;
        private final String breed;

        public Cat(String name, Integer age, String breed) {
            if (name == null || breed == null || age == null) {
                throw new IllegalArgumentException("Arguments cannot be null");
            }
            this.name = name;
            this.age = age;
            this.breed = breed;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public String getBreed() {
            return breed;
        }

        @Override
        public String toString() {
            return "Cat(name=" + name + ", age=" + age + ", breed=" + breed + ")";
        }
    }

    public static List<Cat> generateCats(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        List<Cat> cats = new ArrayList<>();
        String[] names = {"Alex", "Fluff", "Chester", "Choo", "Mittens", "Socks"};
        String[] breeds = {"MAINE_COON", "PERSIAN", "SIBERIAN", "MUNCHKIN", "SIAMESE", "RAGDOLL"};

        for (int i = 0; i < count; i++) {
            String name = names[i % names.length];
            String breed = breeds[i % breeds.length];
            int age = i + 1;
            cats.add(new Cat(name, age, breed));
        }
        return cats;
    }

    public static long generateFood(int familySize, int skip) {
        if (familySize < 0 || skip < 0) {
            throw new IllegalArgumentException("Input arguments cannot be negative");
        }
        if (skip > familySize) {
            return 0;
        }

        long totalFood = 0;
        long currentFood = 4; // Minimum portion of food

        for (int i = 0; i < familySize; i++) {
            if (i >= skip) {
                try {
                    totalFood = Math.addExact(totalFood, currentFood);
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("long overflow");
                }
            }
            currentFood *= 2;
        }

        return totalFood;
    }
}
