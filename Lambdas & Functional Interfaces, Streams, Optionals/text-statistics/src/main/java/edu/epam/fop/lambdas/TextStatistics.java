package edu.epam.fop.lambdas;

import edu.epam.fop.lambdas.Token.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Provides methods to return TokenStatisticsCalculator implementations for different types of token processing.
 */
public final class TextStatistics {

  private TextStatistics() {
    throw new UnsupportedOperationException();
  }

  /**
   * Counts occurrences of tokens in the iterator's sequence and updates the map with these counts.
   *
   * @return a TokenStatisticsCalculator that counts token occurrences.
   */
  public static TokenStatisticsCalculator<Token, Long> countTokens() {
    return (map, tokens) -> {
      while (tokens.hasNext()) {
        Token token = tokens.next();
        map.compute(token, (key, value) -> (value == null) ? 1L : value + 1L);
      }
    };
  }

  /**
   * Counts tokens that are already present in the map up to a specified maxLimit.
   *
   * @param maxLimit the maximum limit for token count
   * @return a TokenStatisticsCalculator that updates the map based on token counts with maxLimit.
   */
  public static TokenStatisticsCalculator<Token, Long> countKnownTokensWithMaxLimit(int maxLimit) {
    return (map, tokens) -> {
        while (tokens.hasNext()) {
            Token token = tokens.next();
            map.computeIfPresent(token, (key, currentValue) -> {
                // Increment the current value if it does not exceed maxLimit
                if (currentValue < maxLimit) {
                    return currentValue + 1;
                }
                return currentValue; // Return unchanged if it exceeds maxLimit
            });
        }
    };
}



  /**
   * Marks tokens that are not in the map and of a specific type.
   *
   * @param types a set of types to specify the token type
   * @return a TokenStatisticsCalculator that marks tokens based on the type set.
   */
  public static TokenStatisticsCalculator<Token, Boolean> findUnknownTokensOfTypes(Set<Type> types) {
    return (map, tokens) -> {
        while (tokens.hasNext()) {
            Token token = tokens.next();
            if (!map.containsKey(token) && types.contains(token.type())) {
                map.computeIfAbsent(token, k -> Boolean.TRUE);
            }
        }
    };
}


  /**
   * Defines a specific code for tokens based on their presence in the map and type.
   *
   * @param maxLimit the maximum limit for token count
   * @param types a set of token types to specify the token type
   * @return a TokenStatisticsCalculator that updates the map with specific codes.
   */
  public static TokenStatisticsCalculator<Token, Integer> combinedSearch(int maxLimit, Set<Type> types) {
    return (map, tokens) -> {
      while (tokens.hasNext()) {
        Token token = tokens.next();
        map.compute(token, (key, value) -> {
          if (value == null) {
            return -1;
          } else if (types.contains(token.type()) && value < maxLimit) {
            return 0;
          } else if (types.contains(token.type()) && value >= maxLimit) {
            return 1;
          } else if (!types.contains(token.type()) && value < maxLimit) {
            return 2;
          } else {
            return 3;
          }
        });
      }
    };
  }
}
