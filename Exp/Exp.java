// class Solution {
//   public int trap(int[] height) {
//     int l = 0, r = height.length-1;
//     int water = 0;
//     int[] lMax = new int[height.length];
//     int[] rMax = new int[height.length];
//
//     lMax[0] = 0;
//     for(int i  = 1; i < lMax.length; i++) {
//       lMax[i] = Math.max(lMax[i-1], height[i-1]);
//     }
//
//     rMax[rMax.length-1] = 0;
//     for(int i  = rMax.length-2; i >= 0; i--) {
//       rMax[i] = Math.max(rMax[i+1], height[i+1]);
//     }
//     return -1;
//   }
// }

// void main() {
  // Solution obj = new Solution();
  // Integer[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
  // IO.println(obj.trap(height));
// }

// public int maxProduct(int[] nums) {
//   int res = Integer.MIN_VALUE;
//   int max = 1;
//   int min = 1;
//   for(int v: nums) {
//     int tmp = max*v;
//     max = Math.max(v, Math.max(tmp, min*v));
//     min = Math.min(v, Math.max(tmp, min*v));
//     res = Math.max(res, max);
//   }
//   return res;
// }
// // public int maxProduct(int[] nums) {
// //   int res = Integer.MIN_VALUE;
// //   for (int n : nums) {
// //     res = Math.max(res, n);
// //   }
// //
// //   int curMax = 1, curMin = 1;
// //
// //   for (int n : nums) {
// //     int temp = curMax * n;
// //     curMax = Math.max(temp, Math.max(curMin * n, n));
// //     curMin = Math.min(temp, Math.min(curMin * n, n));
// //
// //     res = Math.max(res, curMax);
// //   }
// //
// //   return res;        
// // }
// void main() {
//   int[] nums = {-1,-2,-9,-6};
//   IO.println(maxProduct(nums));
// }
//

// class Solution {
//   public int minOperations(String s1, String s2, int x) {
//     int n1 = Integer.parseInt(s1, 2);
//     int n2 = Integer.parseInt(s2, 2);
//     String diff = Integer.toBinaryString(n1^n2);
//
//     int ans = 0;
//
//     int l = 0, r = diff.length()-1;
//     while(diff.charAt(r) != '1') r--;
//     while(r - l > x) {
//       ans += x;
//       l++;r--;
//       while(diff.charAt(l) != '1') l++;
//       while(diff.charAt(r) != '1') r--;
//     }
//
//     int prev = -1;
//     for(int i = 0; i < diff.length(); i++) {
//       if(diff.charAt(i) == '1') {
//         if(prev == -1) prev = i; 
//         else {
//           ans += Math.min(i-prev, x);
//           prev = -1;
//         }
//       }
//     }
//     if(prev != -1) return -1;
//     return ans;
//   }
// }
// public class Exp {
//   public static void main(String[] args) {
//     System.out.println(new Solution().minOperations("11001011111", "01111000110", 2));
//   }
// }
//
//

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.*;

void main() {
  try {
    int[] n = {10};
    n[10] = 1;
    
  } catch(IndexOutOfBoundsException e) {
    e.printStackTrace()
    IO.println(e.getMessage());
    IO.println(e.getCause());
    System.out.print(e);
  } 
}

