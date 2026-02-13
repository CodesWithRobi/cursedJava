import java.util.Scanner;

public class BallHamming {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String X = scanner.next();
    String Y = scanner.next();

    for(int i = 0; i < X.length(); i++) {
      if(X.charAt(i) == Y.charAt(i))
        System.out.print(X.charAt(i) == 'B' ? "W" : "B");
      else
        System.out.print("W");
    }

    scanner.close();
  }
}
