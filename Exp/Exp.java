public class Exp {
//   Exp() { System.out.println("what");}
//   public void Exp() {
//     System.out.println("GOGOGOGO");
//     this();
//   }
    static void foo() { 
      class Outer {
        class Inner extends Outer {
          Inner() {
            System.out.println("Inner me this");
          }
        }
      }
      Outer foo = new Outer().new Inner();
    }


  public static void main(String[] args) {
//     new Exp();
    foo();
  }
}
