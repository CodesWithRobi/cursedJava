class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode merge(ListNode li1, ListNode li2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        while(li1 != null && li2 != null) {
            if(li1.val < li2.val) {
                temp.next = li1;
                li1 = li1.next;
            } else {
                temp.next = li2;
                li2 = li2.next;
            }
        }
        while(li1 != null) {
            temp.next = li1;
            li1 = li1.next;
        }
        while(li2 != null) {
            temp.next = li2;
            li2 = li2.next;
        }
        return head.next;
    }
    public ListNode sortList(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode li2 = sortList(slow.next);
        slow.next = null;
        ListNode li1 = sortList(head);
        ListNode merged = merge(li1, li2);
        return merged;
    }
}

void main() {
  ListNode head = new ListNode();
  ListNode temp = head;
  for(int v: new int[]{4, 2, 1, 3}) {
    temp.next = new ListNode(v);
    temp = temp.next;
  }
  temp = head;
  while(temp.next != null) System.out.print((temp = temp.next).val + "->");
  IO.println();
  head.next = new Solution().sortList(head);
  temp = head;
  while(temp.next != null) System.out.print((temp = temp.next).val + "->");
}
