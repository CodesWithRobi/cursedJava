public class RomanToInteger{
  public static void main(String[] args) {
    String str = "MCMXCIV";
    System.out.println( Solution.romanToInt(str));
  }
}

class Solution {
  public static int romanToInt(String s) {
    int res = 0;
    String symbols = "IVXLCDM";
    int values[] = {1, 5, 10, 50, 100, 500, 1000};

    int curr = 0, prev = 0;

    for(int i = s.length() - 1; i >= 0; i--) {
      curr = values[symbols.indexOf(s.charAt(i))];
      if(curr < prev)
        res-=curr;
      else
        res+=curr;
      prev = curr;
    }
    return res;
  }
}
