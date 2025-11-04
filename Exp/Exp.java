void main() {
  int[] arr = {1, 4, 5, 6};
  var s = List.of(arr).stream().map(String::valueOf).collect(Collectors.joining(", "));
  System.out.println(s);
  throw new RuntimeException(s);
}
