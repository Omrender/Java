package com.epam.rd.autotasks.snail;

import java.util.Scanner;

public class Snail
{
    public static void main(String[] args)
    {
        //Write a program that reads a,b and h (line by lyne in this order) and prints
        //the number of days for which the snail reach the top of the tree.
        //a - feet that snail travels up each day, b - feet that slides down each night, h - height of the tree
         Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt(); // feet climbed per day
        int b = sc.nextInt(); // feet slid per night
        int h = sc.nextInt(); // height of the tree

        // Close scanner to avoid resource leaks
        sc.close();

        // Check if the snail can reach the top in one day
        if (a >= h) {
            System.out.println(1);
        } 
        // Check if it is impossible for the snail to reach the top
        else if (a <= b) {
            System.out.println("Impossible");
        } 
        // Calculate the number of days
        else {
            int netProgressPerDay = a - b;
            int remainingHeight = h - a; // height remaining after the first day
            int daysNeeded = (remainingHeight + netProgressPerDay - 1) / netProgressPerDay + 1; // Use ceiling for division

            System.out.println(daysNeeded);
        }
    }
}
