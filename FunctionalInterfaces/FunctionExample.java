package FunctionalInterfaces;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // Function to get the length of a string
        Function<String, Integer> stringLengthFunction = String::length;
        System.out.println("Length of 'Hello': " + stringLengthFunction.apply("Hello"));

        // Function to square a number
        Function<Integer, Integer> squareFunction = x -> x * x;
        System.out.println("Square of 5: " + squareFunction.apply(5));

        // Chaining functions: get length of string, then square it
        Function<String, Integer> getLengthAndSquare = stringLengthFunction.andThen(squareFunction);
        System.out.println("Length of 'Hello World' squared: " + getLengthAndSquare.apply("Hello World"));
    }
}
