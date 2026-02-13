import java.util.Random;
import java.util.stream.IntStream;

void main() {
  var sb = new StringBuilder();
  IntStream.rangeClosed(1, 1000).parallel().forEach(i -> sb.append(i + " "));
  IO.println(sb);
  IO.println("Length should be 1000: " + sb.chars().filter(i -> i == ' ').count());
  new Random().ints(10, 67, 6767):
}
