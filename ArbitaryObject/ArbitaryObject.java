void main() {
  Function<String, String> func1 = (String s) -> s.toLowerCase();
  Function<String, String> func2 = String::toLowerCase; 
  //both are same!! 
  //Method Reference using Arbitary Object have ClassName::instanceMethod
  
  System.out.println(func2.apply("HELLO"));

  Comparator<Integer> comp1 = (Integer a, Integer b) -> a.compareTo(b);
  Comparator<Integer> comp2 = Integer::compareTo;

  System.out.println(comp2.compare(5, 10));
  //Note compareTo need 1 argument but we pass +1 argument cuz the first would be the object for instance method
  //So for N argument method there must be N+1 arguments

}
