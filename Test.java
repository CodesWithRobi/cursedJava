public class Test {
  Test(String name){
    msg += name;
  }
  void print() {
    System.out.println(msg);
  }
  public static void main(String[] args) {
    new Test("robi").print();
  }
  private String msg = "This is based ";  
}
