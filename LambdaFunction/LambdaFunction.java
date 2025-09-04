
interface Scream {
  void print(String str);
}

public class LambdaFunction {

  static void execute(Runnable action) {
      action.run();
  }

  public static void main(String[] args) {
    Scream me = (str) -> System.out.println(str + " 👀");
    me.print("Bro this is cool!");
    execute(() -> System.out.println("Running!"));
  }
}
