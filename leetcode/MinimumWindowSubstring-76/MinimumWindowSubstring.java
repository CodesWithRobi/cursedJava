import java.util.*;

class Solution {
  public String minWindow(String s, String t) {
    int[] need = new int[128];
    int[] window = new int[128];
    int cnt = 0;
    for(char c: t.toCharArray()) {
      need[c] += 1;
    }
    int n = t.length();
    Queue<Integer> queue = new ArrayDeque<>();
    int ans = Integer.MAX_VALUE;
    char[] sArr = s.toCharArray();
    for(int r = 0; r < s.length(); r++) {
      if(need[sArr[r]] > 0) {
        need[sArr[r]]--;
        queue.add(r);
      }
      if(queue.size() == n) {
        ans = Math.min(ans, r - queue.peek() + 1);
        if(sArr[queue.peek()] == sArr[r]) {
          queue.poll();
          queue.offer(r);
        }
      }
    }
    return "";
  }
  void main() {
    IO.print(minWindow("ADOBECODCEBANCC", "ABCC"));
  }
}
