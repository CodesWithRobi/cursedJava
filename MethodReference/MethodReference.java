class Boomer {
  static void print(String name) {
    System.out.println(name + " is a boomer!");
  }
}

interface Shout {
  void print(String name);
}

public class MethodReference {
  public static void main(String[] args) {
    Shout shout = Boomer::print;
    shout.print("wow");
  }
}
