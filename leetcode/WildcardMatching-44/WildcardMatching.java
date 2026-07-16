class Solution {
  public void isMatch(String s, String p) {
    boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
    dp[0][0] = true;
    for (int i = 0; i < p.length() && p.charAt(i) == '*'; dp[(i++) + 1][0] = true);
    for(int i = 0; i < s.length(); i++) {
      dp[0][i+1] = false;
      for(int j = 0; j < p.length(); j++) {
        if(dp[j+1][i+1])
      }
    }
    



    for(int i = 0; i <= p.length(); i++) {
      for(int j = 0; j <= s.length(); j++) {
        IO.print(dp[i][j] ? "T " : "F ");
      }
      IO.println();
    }
  }
  void main() {
    isMatch("abceb", "*a*b");
  }
}
