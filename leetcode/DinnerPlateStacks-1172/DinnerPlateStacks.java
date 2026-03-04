import java.util.*;
import java.util.concurrent.*;

import com.sun.source.tree.Tree;

// --------------------------------------------------------
// Local Testing Driver
// --------------------------------------------------------
class Main {
    public static void main(String[] args) {
        // Simulating the first example from LeetCode
        System.out.println("Initializing DinnerPlates with capacity 2...");
        DinnerPlates D = new DinnerPlates(2);
        
        D.push(1); System.out.println("Pushed 1");
        D.push(2); System.out.println("Pushed 2");
        D.push(3); System.out.println("Pushed 3");
        D.push(4); System.out.println("Pushed 4");
        D.push(5); System.out.println("Pushed 5");
        
        // Expected layout at this point:
        // Stack 0: [1, 2]
        // Stack 1: [3, 4]
        // Stack 2: [5]
        
        System.out.println("popAtStack(0): " + D.popAtStack(0)); // Expected: 2
        System.out.println("push(20)"); D.push(20);              // Should go to Stack 0
        System.out.println("push(21)"); D.push(21);              // Should go to Stack 2
        
        System.out.println("popAtStack(0): " + D.popAtStack(0)); // Expected: 20
        System.out.println("popAtStack(2): " + D.popAtStack(2)); // Expected: 21
        
        System.out.println("pop(): " + D.pop());                 // Expected: 5
        System.out.println("pop(): " + D.pop());                 // Expected: 4
        System.out.println("pop(): " + D.pop());                 // Expected: 3
        System.out.println("pop(): " + D.pop());                 // Expected: 1
        System.out.println("pop(): " + D.pop());                 // Expected: -1 (Empty)
    }
}

class DinnerPlates {
  List<Deque<Integer>> li;
  TreeSet<Integer> ts;
  int capacity;
  public DinnerPlates(int capacity) {
    li = new ArrayList<>();
    ts = new TreeSet<>();
    this.capacity = capacity;
  }
  
  public void push(int val) {
    if(!ts.isEmpty()) {
      Deque<Integer> stack = li.get(ts.first());

      stack.offerFirst(val);
      if(stack.size() == capacity)
        ts.pollFirst();
      return;
    }
    Deque<Integer> dq;
    if(li.size() != 0 && li.getLast().size() != capacity) {
      dq = li.getLast();
      dq.offerFirst(val);
    } else {
      dq = new ArrayDeque<>();
      dq.offerFirst(val);
      li.add(dq);
    }
  }
    
  public int pop() {
    Integer ans = null;
    while(!li.isEmpty() && (ans = li.getLast().pollFirst()) == null) {
      ts.remove(li.size()-1);
      li.removeLast();
    }
    ts.add(li.size()-1);
    return ans == null ? -1 : ans;
  }
  
  public int popAtStack(int index) {
    if(index >= li.size()) return -1;
    Integer ans = li.get(index).pollFirst(); 
    if(ans == null) return -1;
    else {
      ts.add(index);
      return ans;
    }
  }
}
