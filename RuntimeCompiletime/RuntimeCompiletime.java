import java.util.Arrays;

class A {

  void print() {
    System.out.println("I am A");
  }

}

class B extends A {

  @Override
  void print() {
    System.out.println("I am B");
  }

  void oldPrint() {
    super.print();;
  }

  void test() {
    System.out.println("Test from B");
  }

}

public class RuntimeCompiletime {
  public static void main(String[] args) {
    A objA = new B();
    objA.print(); // Overriden method is called evenif A type cuz runtime JVM check for the object..
    objA.test(); // Compiler found out no test() for class type A

    B objB = new B();
    objB.print();
    objB.test();

    //To print the old one we must use..
    objB.oldPrint();

  }
}
