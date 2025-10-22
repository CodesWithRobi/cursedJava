public class Caching {
  public static void main(String[] args) {
    Integer a = 40, b = 40;
    System.out.println(a == b);
    a = 400;
    b = 400;
    System.out.println(a == b);

    //Compiler just do this
    System.out.println(Integer.valueOf(40) == Integer.valueOf(40));
    System.out.println(Integer.valueOf(400) == Integer.valueOf(400));

    // Integer.valueOf() already have cached objects -128 to 127
    // Just look the definition of valueOf, so same reference witin same value
  }
}
