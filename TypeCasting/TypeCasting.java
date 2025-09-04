import java.util.Scanner;

public class TypeCasting {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Give any value:");
    int v = sc.nextInt();
    System.out.println("Type casting into a byte..");
    v = v * 256 + 42;
    System.out.println("The value becomes " + (byte) v);
    sc.close();
  } 
}
