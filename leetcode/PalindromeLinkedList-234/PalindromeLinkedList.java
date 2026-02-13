class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
  public boolean isPalindrome(ListNode head) {
    if(head.next == null) return true;
    int cnt = 0;
    String ans = "";
    ListNode slow = head, fast = head;
    while(fast != null && fast.next != null) {
      ans += slow.val;
      cnt++;
      slow = slow.next;
      fast = fast.next.next;
    }
    while(slow != null) {
      if(slow.val != ans.charAt(--cnt)-'0') return false;
      slow = slow.next;
    }
    return true;
  }
}

void main() {
  ListNode head = new ListNode(1);
  ListNode temp = head;
  for(int v: new int[]{0, 0}) {
    temp.next = new ListNode(v);
    temp = temp.next;
  }
  System.out.println(new Solution().isPalindrome(head));
}
