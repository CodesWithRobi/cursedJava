// LeetCode ListNode definition
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

class Solution {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int a = 0, b = 0;
    ListNode tempA = headA, tempB = headB;
    while((tempA = tempA.next) != null) a++;
    while((tempB = tempB.next) != null) b++;
    tempA = headA;
    tempB = headB;
    for(;a > b;a--) tempA = tempA.next;
    for(;a < b;b--) tempB = tempB.next;
    while(tempA != null && tempA != tempB) {
      tempA = tempA.next;
      tempB = tempB.next;
    }
    return tempA;
  }
}

public class IntersectionofTwoLinkedLists {
  public static void main(String[] args) {
    // --- TEST CASE SETUP ---
    // listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], intersectVal = 8
    int[] arrA = {4, 1};
    int[] arrB = {5, 6, 1};
    int[] common = {8, 4, 5};

    // 1. Create the common/intersecting part
    ListNode intersectNode = buildList(common);

    // 2. Create headA and attach to intersectNode
    ListNode headA = buildList(arrA);
    attach(headA, intersectNode);

    // 3. Create headB and attach to intersectNode
    ListNode headB = buildList(arrB);
    attach(headB, intersectNode);

    // --- DEBUGGER SECTION ---
    // Set your breakpoint on the line below in NVIM (e.g., :DapContinue)
    Solution sol = new Solution();
    ListNode result = sol.getIntersectionNode(headA, headB);

    if (result != null) {
      System.out.println("Intersected at '" + result.val + "'");
    } else {
      System.out.println("No intersection.");
    }
  }

  // Helper to build a list from array
  private static ListNode buildList(int[] arr) {
    if (arr.length == 0) return null;
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    for (int val : arr) {
      curr.next = new ListNode(val);
      curr = curr.next;
    }
    return dummy.next;
  }

  // Helper to attach the end of one list to another node
  private static void attach(ListNode head, ListNode tail) {
    if (head == null) return;
    ListNode curr = head;
    while (curr.next != null) curr = curr.next;
    curr.next = tail;
  }
}
