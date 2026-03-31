void main() {
  int[] arr1 = {5, 4, 3, 2, 1};
  int[] arr2 = {6, 7, 4, 2, 0};
  Integer[] index = IntStream.range(0, arr1.length).boxed().toArray(Integer[]::new);
  Arrays.sort(index, (a, b) -> arr1[a]-arr1[b]);
  // public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c)
  // this is the signature and so we need Integer[] not int[]
  
  int[] sortedArr1 = Arrays.stream(index).mapToInt(i -> arr1[i]).toArray();
  int[] sortedArr2 = Arrays.stream(index).mapToInt(i -> arr2[i]).toArray();
  IO.println(Arrays.toString(sortedArr1));
  IO.println(Arrays.toString(sortedArr2));
}
