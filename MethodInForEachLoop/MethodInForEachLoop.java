public class MethodInForEachLoop {
  static int[] methodThatReturnsArray() {
    return new int[]{5, 6, 1, 2, 9};
  }
  public static void main(String[] args) {
    for (int v : methodThatReturnsArray()) {
      System.out.println("Value -> " + v);
    }
  }
}
