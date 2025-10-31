import java.util.stream.Stream;

void main() {
  var ans = "[" + Stream.of(1, 2, 3, 4, 5, 6).map(e -> Integer.toString(e)).collect(Collectors.joining(", ")) + "]";
  IO.println(ans);
}
