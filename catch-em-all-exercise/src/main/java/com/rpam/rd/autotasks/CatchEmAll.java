package com.rpam.rd.autotasks;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CatchEmAll {

    // You may set another exception in this field;
    static Exception exception = new FileNotFoundException();

    public static void riskyMethod() throws Exception {
        throw exception;
    }

    public static void main(String[] args) throws Exception {
        try {
            riskyMethod();
        } catch (IOException e) {
            // Check specifically for FileNotFoundException
            if (e instanceof FileNotFoundException) {
                throw new IllegalArgumentException("Resource is missing", e);
            } else {
                throw new IllegalArgumentException("Resource error", e);
            }
        } catch (ArithmeticException | NumberFormatException e) {
            // Print the message of the exception to System.err
            System.err.println(e.getMessage());
        }
        // Do not catch other exceptions; they will be propagated
    }
}
