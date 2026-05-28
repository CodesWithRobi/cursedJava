package FunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {
    public static void main(String[] args) {
        // Predicate to check if a number is even
        Predicate<Integer> isEven = x -> x % 2 == 0;

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Using predicate to filter a list of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .collect(Collectors.toList());

        System.out.println("
Even numbers in the list: " + evenNumbers);

        // Predicate to check if a string has length greater than 5
        Predicate<String> isLongerThan5 = s -> s.length() > 5;
        List<String> words = Arrays.asList("hello", "world", "java", "functional", "interface");
        List<String> longWords = words.stream()
                .filter(isLongerThan5)
                .collect(Collectors.toList());

        System.out.println("Words longer than 5 characters: " + longWords);
    }
}
