// Java collections are heavily interface driven!

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
