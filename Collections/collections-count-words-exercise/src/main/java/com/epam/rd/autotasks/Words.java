package com.epam.rd.autotasks;

import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        // Map to store word counts
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Process each line in the list of lines
        for (String line : lines) {
            // Split the line into words by non-letter characters (handles punctuation and other symbols)
            String[] words = line.split("[^\\p{L}]+");

            // For each word in the split line
            for (String word : words) {
                word = word.trim(); // Remove leading/trailing whitespace
                if (word.length() >= 4) {
                    word = word.toLowerCase(); // Normalize word to lowercase
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Create a list of entries (word - count) filtered by word frequency
        List<Map.Entry<String, Integer>> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() >= 10) { // Only consider words appearing at least 10 times
                entries.add(entry);
            }
        }

        // Sort the entries by frequency descending, then by word alphabetically
        entries.sort((entry1, entry2) -> {
            int compareByFrequency = entry2.getValue().compareTo(entry1.getValue());
            if (compareByFrequency != 0) {
                return compareByFrequency;
            } else {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        // Return the result as a string
        return result.toString().trim(); // Remove final unnecessary newline
    }
}
