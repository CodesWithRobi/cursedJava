package FunctionalInterfaces;

import java.util.function.Supplier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SupplierExample {
    public static void main(String[] args) {
        // Supplier to get the current date and time
        Supplier<String> dateTimeSupplier = () -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return now.format(formatter);
        };

        System.out.println("Current Date and Time: " + dateTimeSupplier.get());

        // Supplier to provide a random number
        Supplier<Double> randomNumberSupplier = Math::random;
        System.out.println("Random Number: " + randomNumberSupplier.get());
    }
}
