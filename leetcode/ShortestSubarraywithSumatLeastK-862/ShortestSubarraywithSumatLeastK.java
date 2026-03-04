import java.util.Arrays;
public int shortestSubarray(int[] A, int k) {
 int[] B = new int[A.length+1];
  int ans = Integer.MAX_VALUE;
  for(int i = 0; i < A.length; i++) B[i+1]=B[i]+A[i];
  Deque<Integer> dq = new ArrayDeque<>();
  for(int i = 0; i < B.length; i++) {
    while(!dq.isEmpty() && B[i]-B[dq.getFirst()]>=k)
      ans = Math.min(ans, i-dq.removeFirst());
    while(!dq.isEmpty() && B[dq.getLast()] > B[i]) dq.removeLast();
    dq.add(i);
  }
  return ans == Integer.MAX_VALUE ? -1 : ans;
}

void main() {
  int[] input = new int[(int)Math.pow(10, 5)];
  Arrays.fill(input, -100000);
  System.out.println(shortestSubarray(input, 1000000000));
  // System.out.println(shortestSubarray(new int[]{-28,81,-20,28,-29}, 89));
}

// dq.stream().forEach(o -> IO.println(java.util.Arrays.toString(o)));
