interface A {
  default void print() {
    System.out.println("I am A");
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

  @Override
  public void print() { // Can't do it without Override.. lol
    System.out.println("I AM HUNGRY");
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
  }
}
