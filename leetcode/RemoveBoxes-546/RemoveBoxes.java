class Solution {
  int[] boxes;
  int[][][] dp;

  public int removeBoxes(int[] boxes) {
    this.boxes = boxes;
    int n = boxes.length;
    dp = new int[n][n][n];
    return solve(0, n - 1, 0);
  }

  int solve(int l, int r, int k) {
    if(l > r) return 0;
    while(l < r && boxes[r-1] == boxes[r]) {
      k++;r--;
    }
    if(dp[l][r][k] != 0) return dp[l][r][k];
    int ans = solve(l, r-1, 0)+(k+1)*(k+1);
    for(int i = l; i < r; i++) {
      if(boxes[i]==boxes[r]) {
        ans = Math.max(ans, solve(l, i, k+1)
        + solve(i+1, r-1, 0));
      }
    }
    return dp[l][r][k] = ans;
  }

  void main() {
    IO.println(removeBoxes(new int[]{1}));
  }
}
