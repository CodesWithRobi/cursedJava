import java.util.Arrays;
import java.util.PriorityQueue;

// MAX HEAP:
// int[] topKFrequent(int[] nums, int k) {
//   Map<Integer, Integer> hash = new HashMap<>();
//   Arrays.stream(nums).forEach(i -> hash.put(i, hash.getOrDefault(i, 0)+1));
//   PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b) -> b.getValue()-a.getValue());
//   pq.addAll(hash.entrySet());
//   int[] res = new int[k];
//   for(int i = 0; i < k; res[i++]=pq.poll().getKey());
//   return res;
// }

// MIN HEAP:
int[] topKFrequent(int[] nums, int k) {
  Map<Integer, Integer> hash = new HashMap<>();
  Arrays.stream(nums).forEach(i -> hash.put(i, hash.getOrDefault(i, 0)+1));
  PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b) -> a.getValue()-b.getValue());
  for(Map.Entry<Integer, Integer> i: hash.entrySet()) {
    pq.add(i);
    if(pq.size()>k) {
      pq.poll();
    }
  }
  int[] res = new int[k];
  for(int i = 0; i < k; res[i++]=pq.poll().getKey());
  return res;
}

void main() {
  IO.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
}
