void main() {
  // int[] arr = new int[10];
  // Arrays.fill(arr, -1); //pfff..
  int[] arr = Arrays.stream("-1 ".repeat(10).split(" ")).mapToInt(Integer::parseInt).toArray();
  IO.print(Arrays.toString(arr));
}
