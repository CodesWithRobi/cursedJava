import java.util.*;

class Solution {
  public List<Integer> fallingSquares(int[][] positions) {
    int[] roof = new int[1000];
    int maxTop = 0;
    List<Integer> li = new ArrayList<>();
    for (int[] square : positions) {
      int l = square[0];
      int h = square[1];
      int r = l + h;

      int top = 0;
      for (int i = l+1; i <= r; i++) {
        top = Math.max(top, roof[i]);
      }
      for (int i = l+1; i <= r; i++) {
        roof[i] = top + h;
      }
      maxTop = Math.max(maxTop, top+h);
      li.add(maxTop);
    }
    return li;
  }
  void main() {
    IO.println(fallingSquares(new int[][]{{9,7},{1,9},{3,1}}));
    // IO.println(fallingSquares(new int[][]{{100, 100},{200, 100}}));
  }
}
