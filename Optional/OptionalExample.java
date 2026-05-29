package Optional;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        // Creating Optional objects
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> presentOptional = Optional.of("Hello");
        Optional<String> nullableOptional = Optional.ofNullable(null);
        Optional<String> nullablePresentOptional = Optional.ofNullable("World");

        // isPresent() and isEmpty()
        System.out.println("emptyOptional is present: " + emptyOptional.isPresent()); // false
        System.out.println("presentOptional is present: " + presentOptional.isPresent()); // true
        System.out.println("emptyOptional is empty: " + emptyOptional.isEmpty()); // true
        System.out.println("presentOptional is empty: " + presentOptional.isEmpty()); // false


        // orElse() - get the value or a default value
        System.out.println("\orElse():");
        System.out.println(emptyOptional.orElse("Default Value")); // "Default Value"
        System.out.println(presentOptional.orElse("Default Value")); // "Hello"

        // orElseGet() - get the value or compute a default value
        System.out.println("\orElseGet():");
        System.out.println(emptyOptional.orElseGet(() -> "Computed Default")); // "Computed Default"
        System.out.println(presentOptional.orElseGet(() -> "Computed Default")); // "Hello"

        // orElseThrow() - get the value or throw an exception
        System.out.println("\orElseThrow():");
        try {
            emptyOptional.orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("emptyOptional throws exception as expected");
        }
        System.out.println(presentOptional.orElseThrow(IllegalArgumentException::new)); // "Hello"

        // ifPresent() - perform an action if the value is present
        System.out.println("
ifPresent():");
        presentOptional.ifPresent(value -> System.out.println("Value is: " + value)); // "Value is: Hello"
        emptyOptional.ifPresent(value -> System.out.println("This won't be printed"));

        // map() - transform the value if present
        System.out.println("
map():");
        Optional<Integer> lengthOptional = presentOptional.map(String::length);
        lengthOptional.ifPresent(len -> System.out.println("Length of value: " + len)); // "Length of value: 5"
        Optional<Integer> emptyLengthOptional = emptyOptional.map(String::length);
        System.out.println("emptyLengthOptional is present: " + emptyLengthOptional.isPresent()); // false


        // filter() - filter the value based on a predicate
        System.out.println("
filter():");
        Optional<String> filteredOptional = presentOptional.filter(s -> s.startsWith("H"));
        filteredOptional.ifPresent(val -> System.out.println("Filtered value: " + val)); // "Filtered value: Hello"
        Optional<String> notFilteredOptional = presentOptional.filter(s -> s.startsWith("W"));
        System.out.println("notFilteredOptional is present: " + notFilteredOptional.isPresent()); // false
    }
}
