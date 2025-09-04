public class EffectivelyFinal {
  public static void main(String[] args) {
    int x = 10; // effectively final
    Runnable r = () -> System.out.println(x); // valid
    // x = 11; // compile error if uncommented
    new Thread(r).start();
  }
}
