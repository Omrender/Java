package edu.epam.fop.lambdas;

import java.util.function.DoubleFunction;

public interface PercentageFormatter {

  // Lambda expression to format double values as percentage strings
  DoubleFunction<String> INSTANCE = d -> {
    // Convert the double value to a percentage
    double percent = d * 100;

    // Round to one decimal place
    double roundedPercent = Math.round(percent * 10) / 10.0;

    // Format the result with or without decimal places
    return roundedPercent % 1 == 0
           ? String.format("%d %s", (int) roundedPercent, "%")
           : String.format("%.1f %s", roundedPercent, "%");
  };
}
