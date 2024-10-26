package com.epam.rd.autotasks.godutch;

import java.util.Scanner;

public class GoDutch {

    public static void main(String[] args) {
        //Write code here
        Scanner sc = new Scanner(System.in);

        // Read the total bill amount
        int billAmount = sc.nextInt();

        // Check if the bill amount is negative
        if (billAmount < 0) {
            System.out.println("Bill total amount cannot be negative");
            return;
        }

        // Read the number of friends
        int numberOfFriends = sc.nextInt();

        // Check if the number of friends is zero or negative
        if (numberOfFriends <= 0) {
            System.out.println("Number of friends cannot be negative or zero");
            return;
        }

        // Calculate the total amount including 10% tips
        int totalAmountWithTips = (billAmount * 110) / 100;

        // Calculate the part each friend has to pay
        int partToPay = totalAmountWithTips / numberOfFriends;

        // Print the result
        System.out.println(partToPay);

        sc.close();
    }
}
