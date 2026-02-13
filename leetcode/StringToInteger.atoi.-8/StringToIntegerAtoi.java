public class StringToIntegerAtoi {
  public static void main(String[] args) {
    String s = "-91283472332";
    System.out.println(Solution.myAtoi(s));
  }
}

class Solution {
    public static int myAtoi(String s) {
        boolean neg = false;
        int i = 0;
        int ans = 0;
        s = s.trim();
        switch(s.charAt(0)) {
            case '-':
                neg = true;
            case '+':
                i = 1;
                break;
        }
        while(s.charAt(i) == '0') i++;
        while(i < s.length()) {
            if(!Character.isDigit(s.charAt(i))) break;
            ans =(ans*10) + (s.charAt(i) - '0');
            i++;
        }
        return neg ? -ans : ans;
    }
}
