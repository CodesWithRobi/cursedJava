interface A {
  default void print() {
    System.out.println("I am A");
  }

  static void tisStaticDefMethod() {
    IO.print("JAVA 25 babyyy!!");
  }

  void call();
}

interface B {
  default void print() { //Make this void -> int, its so funny!!
    System.out.println("I am B");
  }

  void kewl();
}

class Cls implements A, B {

  // @Override
  // public void print() { // Either override
  //   System.out.println("I AM HUNGRY");
  // }

  public void print() {
    A.super.print(); //This is so cursed why its not like super<A>.print() or smthng
    //This is like referencing outerclass from the inner class Outer.this.method()
  }

  public void call() {
    System.out.println("NEW CALL BABY");
  }

  public void kewl() {
    System.out.println("I am so kewl");
  }
}

public class DefaultMethod {
  public static void main(String[] args) {
    Cls obj = new Cls();
    A.tisStaticDefMethoa();
  }
}
