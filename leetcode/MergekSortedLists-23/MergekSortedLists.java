class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

ListNode mergeKLists(ListNode[] lists) {
  PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
  for(ListNode first: lists) {
    if(first != null)
      heap.offer(first);
    }
  }
  ListNode head = heap.poll();
  ListNode temp = head;
  while(!heap.isEmpty()) {
    temp.next = heap.poll();
    temp = temp.next;
  }
  return head;
}

void main() {
  // Example input: lists = [[1,4,5],[1,3,4],[2,6]]
  int[][] input = {
    {-2, -1, -1, -1},
    {}
  };

  // Convert the 2D array into an array of ListNodes
  ListNode[] lists = new ListNode[input.length];
  for (int i = 0; i < input.length; i++) {
    lists[i] = buildList(input[i]);
  }

  // Run the algorithm
  ListNode mergedHead = mergeKLists(lists);

  // Print the output (Expected: [1, 1, 2, 3, 4, 4, 5, 6])
  System.out.print("Merged List: ");
  printList(mergedHead);
}
ListNode buildList(int[] arr) {
  if (arr == null || arr.length == 0) return null;
  ListNode dummy = new ListNode(0);
  ListNode current = dummy;
  for (int num : arr) {
    current.next = new ListNode(num);
    current = current.next;
  }
  return dummy.next;
}

void printList(ListNode head) {
  if (head == null) {
    System.out.println("[]");
    return;
  }
  System.out.print("[");
  while (head != null) {
    System.out.print(head.val + (head.next != null ? ", " : ""));
    head = head.next;
  }
  System.out.println("]");
}
