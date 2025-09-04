class Person {
  String name;
  { name = "Steve"; }
}

public class InstantiationVsInitialization {
  public static void main(String[] args) {
    // System.out.println(new Person().name);
    Person[] boys = new Person[1];
    System.out.println(boys[0].name);
  }
}
