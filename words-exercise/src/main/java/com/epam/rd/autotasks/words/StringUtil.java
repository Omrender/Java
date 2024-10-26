package com.epam.rd.autotasks.words;

import java.util.Arrays;

public class StringUtil {

    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if (words == null || words.length == 0 || sample == null) {
            return 0;
        }

        int count = 0;
        sample = sample.trim().toLowerCase();

        for (String word : words) {
            if (word.trim().toLowerCase().equals(sample)) {
                count++;
            }
        }

        return count;
    }

    public static String[] splitWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }

        // Split text using the specified separators
        String[] words = text.split("[,.;: !?]+");

        // Filter out empty strings
        return words.length == 0 || (words.length == 1 && words[0].isEmpty()) ? null : words;
    }

    public static String convertPath(String path, boolean toWin) {
        if (path == null) {
            return null;
        }

        if (toWin) {
            return convertUnixToWin(path);
        } else {
            return convertWinToUnix(path);
        }
    }

    // Method to convert a given path from Windows to Unix format
    public static String convertWinToUnix(String path) {
        if (path == null || path.isEmpty()) {
            return null; // Return null for empty or null input
        }

        // Replace backslashes with forward slashes for Unix format
        String unixPath = path.replace("\\", "/");
        return unixPath;
    }

    // Method to convert a given path from Unix to Windows format
    public static String convertUnixToWin(String path) {
        if (path == null || path.isEmpty()) {
            return null; // Return null for empty or null input
        }

        // Replace forward slashes with backslashes for Windows format
        String windowsPath = path.replace("/", "\\");
        return windowsPath;
    }

    public static String joinWords(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder("[");
        boolean isEmpty = true;

        for (String word : words) {
            if (!word.isEmpty()) {
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(word);
                isEmpty = false;
            }
        }

        sb.append("]");

        return isEmpty ? null : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS",};
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}
