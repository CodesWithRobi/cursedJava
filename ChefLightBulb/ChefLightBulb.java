import java.util.Scanner;

public class ChefLightBulb {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int i = 0, mod = 0, cnt = 0;
    int N = scanner.nextInt();
    int p = scanner.nextInt();
    int K = scanner.nextInt();
    for(int j = 0; i != p; j++) {
      if(i >= N) {
        mod++;
        j = 0;
        cnt--;
      }
      i = j*K + mod;
      cnt++;
    }
    System.out.println(cnt);
    scanner.close();
  }
}
