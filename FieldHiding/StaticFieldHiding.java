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
    System.out.println(A.ch);
    System.out.println(B.ch);

    System.out.println("Methods:"); // Polymorphic
    A.print();
    B.print();
  }
}
