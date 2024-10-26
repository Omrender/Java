package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {
        // Put your code here
        if (email == null) {
            return false;
        }

        // Define the regex pattern for a valid EPAM email
        String epamEmailPattern = "^[a-zA-Z]+_[a-zA-Z]+[0-9]*@epam\\.com$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(epamEmailPattern);

        // Match the pattern with the input email
        Matcher matcher = pattern.matcher(email);

        // Return whether the input matches the pattern
        return matcher.matches();


    }
}





