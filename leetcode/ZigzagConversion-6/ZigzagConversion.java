public class ZigzagConversion {
  public static void main(String[] args) {

    String s = "A";
    int numRows =  1;
    System.out.println(Solution.convert(s, numRows));
    
  }
}

class Solution {
  public static String convert(String s, int numRows) {
    int incBy;
    if(numRows == 1) return s;
    StringBuilder str = new StringBuilder();
    for(int i = 0; i < s.length(); i+=(numRows-1)*2) {
      str.append(s.charAt(i));
    }
    for(int i = 1; i < numRows-1; i++) {
      incBy = (numRows-1)*2;
      for(int j = i, inc = incBy-(i*2); j < s.length(); j+=inc, inc = incBy-inc) {
      str.append(s.charAt(j));
      }
      System.out.print("");
    }
    for(int i = numRows-1; i < s.length(); i+=(numRows-1)*2) {
      str.append(s.charAt(i));
    }
    return str.toString();
  }
}
