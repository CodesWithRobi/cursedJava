import java.util.PriorityQueue;

int[] smallestRange(List<List<Integer>> nums) {
  int start = 0, end = Integer.MAX_VALUE;
  int cmax = Integer.MIN_VALUE;
  PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0]-b[0]));
  for(int i = 0; i < nums.size(); i++) {
    int val = nums.get(i).get(0);
    pq.add(new int[]{val, i, 0});
    cmax = Math.max(cmax, val);
  }
  while(true) {
    int[] min = pq.poll();
    if(cmax-min[0] < end - start) {
      end = cmax;
      start = min[0];
    }
    if(min[2]+1 < nums.get(min[1]).size()) { //don't go out of bound
      int val = nums.get(min[1]).get(min[2]+1);
      pq.add(new int[]{val, min[1], min[2]+1});
      cmax = Math.max(cmax, val);
    } else return new int[]{start, end};
  }
}

void main() {
  List<List<Integer>> nums = List.of(List.of(4,10,15,24,26), List.of(0,9,12,20), List.of(5,18,22,30));
  IO.print(Arrays.toString(smallestRange(nums)));
}
