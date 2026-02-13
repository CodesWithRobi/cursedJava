public class CountPrimes {
  public static void main(String[] args) {
    int n = 50000;
    int ans = Solution.countPrimes(n);
    System.out.println(ans);
  }
}
//Sieve of Eratosthenes.
class Solution {
    public static int countPrimes(int n) {
    int[] nums = new int[n];
    nums[0] = nums[1] = 1;
    int cnt = 0;
    for(int i = 2; i < n; i++) {
      if(nums[i] == 0) {
        cnt++;
        for(int j = i*2; j < n; j+=i) 
          nums[j] = 1;
      }
    }
    return cnt; 
  }
}
