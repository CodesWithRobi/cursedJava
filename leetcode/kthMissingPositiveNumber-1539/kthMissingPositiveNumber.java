public class kthMissingPositiveNumber {
  public static void main(String[] args) {
    int[] arr = {2,3,4,7,11};
    int k = 5;
    int ans = Solution.findKthPositive(arr, k);
    System.out.println(ans);
  }
}
class Solution {
  public static int findKthPositive(int[] arr, int k) {
    int l = 0, r = arr.length-1, mid;
    while(l <= r) {
      mid = l + (r-l)/2;
      if(arr[mid]-mid-1 < k) {
        l = mid+1;
      }
      else {
        r = mid-1;
      }
    }
    return k+l;
  }
}
