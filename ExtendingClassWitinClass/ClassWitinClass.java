//class HelloThere {
//  static void bruh(int i) {
//    if(i == 10) return;
//    System.out.println(i);
//    HiThere.thisIsWild(i+1);
//  }
//  class HiThere extends HelloThere {
//    static void thisIsWild(int i) {
//      HiThere.bruh(i);
//    }
//  }
//}
//
class HelloThere {
  public static int cnt = 0;

  class HiThere {
    HelloThere obj = new HelloThere();
    HiThere() {cnt++;}
  }
  
  HelloThere() {cnt++;}
}

public class ClassWitinClass {
  public static void main(String[] args) {
    new HelloThere().new HiThere().obj.new HiThere().obj.new HiThere().obj.new HiThere().obj.new HiThere();
    System.out.println("Depth = " + HelloThere.cnt);
  }
}
