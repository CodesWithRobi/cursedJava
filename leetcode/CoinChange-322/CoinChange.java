//This doesn't work cuz this is a greedy possibility, but answer is not inside those combinations alone
import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int ans = 0, len = coins.length-1, amt = amount;
        Arrays.sort(coins);
        while(len != 0) {
    
            for(int i = len; i >= 0; i--) {
                ans += amt/coins[i];
                amt %= coins[i];
            }
            if(amt == 0) return ans;
            len--;
            amt = amount;
            ans = 0;
        }
        return -1;
    }
}

public class CoinChange {
  public static void main(String[] args) {
    int[] coins = {186,419,83,408};
    System.out.println(new Solution().coinChange(coins , 6249));
  }
}
