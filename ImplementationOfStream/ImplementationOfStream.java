import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

//This bad boy contains the implementations of Stream!!
import java.util.stream.ReferencePipeline;

public class ImplementationOfStream {
  public static void main(String[] args) {

    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
                              // Stream<Integer> -> IntStream (primitive types)

    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());

  }
}
