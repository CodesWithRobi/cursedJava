class A {
  public static int what = 0;
  class B extends A {
    B() {
      what++;
    }
  }
}

public class ExtendingClassWitinClass {
  public static void main(String[] args) {
    A.B a  = new A().new B().new B().new B();
    System.out.println("Depth = " + A.B.B.B.B.what);
  }
}
