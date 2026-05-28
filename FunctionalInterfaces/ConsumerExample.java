package FunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        // Consumer to print a string to the console
        Consumer<String> printConsumer = System.out::println;
        printConsumer.accept("Hello, Consumer!");

        // Consumer to square a number and print it
        Consumer<Integer> squareConsumer = (x) -> System.out.println("Square of " + x + " is: " + (x * x));
        squareConsumer.accept(5);

        // Using consumer with a list of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("
Using consumer with a list of numbers:");
        numbers.forEach(squareConsumer);
    }
}
