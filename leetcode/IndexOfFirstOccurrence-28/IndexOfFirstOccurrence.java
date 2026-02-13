public class IndexOfFirstOccurrence {
  public static void main(String[] args) {
    String haystack = "a";
    String needle = "a";
    int res = Solution.strStr(haystack, needle);
    System.out.println(res);
  }
}

class Solution {
    public static int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        int i = haystack.indexOf(needle.charAt(0));
        if(i == -1) return -1;
        for(; i < hLen-nLen; i++) {
            if(haystack.substring(i, nLen+i).equals(needle))
                return i;
        }
        return -1;
    }
}
