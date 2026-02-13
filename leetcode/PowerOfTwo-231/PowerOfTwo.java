public class PowerOfTwo {
  public static void main(String[] args) {
    System.out.println(Solution.isPowerOfTwo(4));
  }
}

class Solution {
  public static boolean isPowerOfTwo(int n) {
    return 1 == Integer.bitCount(n);
  }
}

