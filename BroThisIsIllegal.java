class Aaron{
  public static int n = 10;
  void aaron(Aaron aaron){
    System.out.println("Aaron is great!");
    n--;
    if(n > 0) aaron.aaron(aaron);
  }
}
public class BroThisIsIllegal {
  public static void main(String[] args) {
    Aaron aaron = new Aaron();
    aaron.aaron(aaron);
  }
}
