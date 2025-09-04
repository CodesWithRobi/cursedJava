public class VarargsWithGenerics {
  @SafeVarargs //Brudha this is dangerous brudha
  static <T> void weird(T... args) {
    for (T t : args) System.out.println(t);
  }
  public static void main(String[] args) {
    weird(1, 2, 3);
    weird("noob", "master", "69");
  }
}
