public class NumberComplement {
  public static void main(String[] args) {
    int n = 2;
    int nBit = (int) (Math.log(n)/Math.log(2) + 1);
    int mask = (1<<nBit)-1;
    System.out.println(n^mask); 
  }
}
