package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String colorCode) {

        // Put your code here
        if (colorCode == null) {
            return false;
        }

        // Define the regex pattern for a valid HTML color code
        // This pattern allows both six-digit and three-digit color codes
        String hexColorPattern = "^#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(hexColorPattern);

        // Match the pattern with the input color code
        Matcher matcher = pattern.matcher(colorCode);

        // Return whether the input matches the pattern
        return matcher.matches();


    }
}





