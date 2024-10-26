package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the input value (seconds since midnight)
        int totalSeconds = scanner.nextInt();
        
        // Calculate hours, minutes, and seconds
        int hours = (totalSeconds / 3600) % 24;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
       // Format the output as h:mm:ss
        System.out.printf("%d:%02d:%02d\n", hours, minutes, seconds);
    }
}
