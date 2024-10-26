package edu.epam.fop.lambdas;

import java.util.function.Function;

/**
 * A functional interface that represents a function which can throw a checked exception.
 * 
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <E> the type of the exception the function can throw
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {
    
    R apply(T t) throws E;

    /**
     * Wraps a ThrowingFunction into a Function, handling any thrown exceptions.
     * 
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @param <E> the type of the exception the function can throw
     * @param throwingFunction the ThrowingFunction to wrap
     * @return a Function that wraps the ThrowingFunction and catches any thrown exceptions
     */
    static <T, R, E extends Throwable> Function<T, R> quiet(ThrowingFunction<T, R, E> throwingFunction) {
        return t -> {
            if (t == null) {
                return null;
            }
            try {
                return throwingFunction.apply(t);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
