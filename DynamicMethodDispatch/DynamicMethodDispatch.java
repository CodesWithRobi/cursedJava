class Task implements Runnable {
  private void print() {
    System.out.println("Does this works?");
  }
  @Override
  public void run() {
    print();
  }
}

public class DynamicMethodDispatch {
  public static void main(String[] args) {
    Runnable obj = new Task();
    obj.run();
  }
}
