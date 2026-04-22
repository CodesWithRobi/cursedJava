class Parent {
  static { System.out.println("1. Parent Static Block"); }
  { System.out.println("3. Parent Instance Block"); }
  Parent() { System.out.println("4. Parent Constructor"); }
}

class OrderofInitialization extends Parent {
  static { System.out.println("2. Child Static Block"); }
  { System.out.println("5. Child Instance Block"); }
  OrderofInitialization() { System.out.println("6. Child Constructor"); }

  public static void main(String[] args) {
    System.out.println("--- Starting Main ---");
    new OrderofInitialization();
  }
}
