package com.epam.rd.autotasks.meetstrangers;

import java.io.IOException;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".
        Scanner sc = new Scanner(System.in);

        // Read the number of strangers
        int numberOfStrangers = sc.nextInt();
        
        // Consume the remaining newline character if there are more lines to read
        if (sc.hasNextLine()) {
            sc.nextLine(); 
        }

        // Handle special cases for zero and negative inputs
        if (numberOfStrangers == 0) {
            System.out.println("Oh, it looks like there is no one here");
        } else if (numberOfStrangers < 0) {
            System.out.println("Seriously? Why so negative?");
        } else {
            // Read stranger names and print greetings
            for (int i = 0; i < numberOfStrangers; i++) {
                if (sc.hasNextLine()) {
                    String name = sc.nextLine();
                    System.out.println("Hello, " + name);
                }
            }
        }

        sc.close();
    }
}
