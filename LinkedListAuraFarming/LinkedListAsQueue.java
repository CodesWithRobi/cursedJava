// Java collections are heavily interface driven!

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

void main() {
  List<Integer> li;
  li = new LinkedList<>();
  li = new ArrayList<>();
  li = new Vector<>(); //Legacy
  li = new Stack<>(); //Legacy

  Queue<Integer> q;
  q = new LinkedList<>();
  q = new PriorityQueue<>();
  q = new ArrayDeque<>();

  Deque<Integer> dq;
  dq = new LinkedList<>();
  dq = new ArrayDeque<>();

  Set<Integer> set;
  set = new LinkedHashSet<>();
  set = new HashSet<>();
  set = new TreeSet<>(); //This is freaking TreeMap behind

  Map<Integer, Integer> map;
  map = new LinkedHashMap<>();
  map = new HashMap<>();
  map = new TreeMap<>();
  map = new Hashtable<>(); //Legacy
  
  Queue<Integer> que = Collections.asLifoQueue(new ArrayDeque<>());

}
