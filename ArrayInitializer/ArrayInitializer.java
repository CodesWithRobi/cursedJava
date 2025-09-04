public class ArrayInitializer {

  public static void main(String[] args) {
    System.out.println("This works".getClass());
    // System.out.println({"test", "test2", "test3"}.getClass()); -> Not possible
    System.out.println(new String[]{"test", "test2", "test3"}.getClass());
  }
  
}
