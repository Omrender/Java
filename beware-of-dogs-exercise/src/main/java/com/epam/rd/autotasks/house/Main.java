package com.epam.rd.autotasks.house;

import com.epam.rd.autotasks.residents.cats.Cat;
import com.epam.rd.autotasks.residents.cats.Kitten;
import com.epam.rd.autotasks.residents.dogs.Dog;
import com.epam.rd.autotasks.residents.dogs.Puppy;

public class Main {

    public static void main(String[] args) {
        Dog rex = new Dog("Rax");
        Puppy randy = new Puppy("Randy");
        Cat barbos = new Cat("Barbos");
        Kitten murzik = new Kitten("Murzik");

        // Parameterized House for Dogs
        House<Dog> dogHouse = new House<>();
        dogHouse.enter(rex);
        dogHouse.enter(randy);
        // dogHouse.enter(murzik); // This line will now cause a compilation error
        System.out.println(dogHouse);

        // Parameterized House for Cats
        House<Cat> catHouse = new House<>();
        catHouse.enter(barbos);
        catHouse.enter(murzik);
        // catHouse.enter(rex); // This line will now cause a compilation error
        System.out.println(catHouse);
    }
}
