import java.util.PriorityQueue;

class Solution {
  public long minimumDifference(int[] nums) {
    int n = nums.length/3;
    PriorityQueue<Integer> topMin = new PriorityQueue<>((a,b)->b-a); //MaxHeap
    PriorityQueue<Integer> topMax = new PriorityQueue<>(); //MinHeap
    long[] left = new long[nums.length];
    long[] right = new long[nums.length];
    int sum = 0;
    for(int i = 0; i < nums.length; i++) {
      sum += nums[i];
      topMin.offer(nums[i]);
      if(topMin.size() == n+1) sum -= topMin.poll();
      left[i] = sum;
    }
    for(int i = nums.length-1; i >= 0; i--) {
      sum += nums[i];
      topMax.offer(nums[i]);
      if(topMax.size() == n+1) sum -= topMax.poll();
      left[i] = sum;
    }
    long min = Long.MAX_VALUE;
    for(int i = n-1; i < 2*n; i++) {
      min = Math.min(min, left[i]-right[i+1]);
    }
    return min;
  }
  void main() {
    IO.print(minimumDifference(new int[]{7,9,5,8,1,3}));
  }
}
