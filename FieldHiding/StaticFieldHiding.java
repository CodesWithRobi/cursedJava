class A {
  static char ch = 'a';
  static void print() {
    System.out.println(ch);
  }
}
class B extends A {
  static char ch = 'b';
  static void print() {
    System.out.println(ch);
  }
}

public class StaticFieldHiding {
  public static void main(String[] args) {
    System.out.println("Variables:"); // Not Polymorphic
    System.out.println(new B().ch);
    A objA = new B();
    System.out.println(objA.ch);

    System.out.println("Methods:"); // Polymorphic
    new B().print();
    objA.print();
  }
}
