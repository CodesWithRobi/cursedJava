class Test {
  public static String String(String str) {
    return new String(str);
  }
  public static void main(String[] args) {
    String aaron = "string";
    String robinson = String("string");
    System.out.println(aaron == robinson);
  }
}

