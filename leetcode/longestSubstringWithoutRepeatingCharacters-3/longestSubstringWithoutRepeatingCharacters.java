import java.util.Arrays;

public class longestSubstringWithoutRepeatingCharacters {
  public static void main(String[] args) {
    String s = "bbbb";
    
    int n = s.length();
    int l = 0;
    int maxLen = 0;

    int[] hash = new int[128];
    Arrays.fill(hash, -1);

    for(int r = 0; r < n; r++) {
      if(hash[s.charAt(r)] >= l) {
        l = hash[s.charAt(r)] + 1;
      }
      hash[s.charAt(r)] = r;
      maxLen = Math.max(maxLen, r-l+1);
    }
    System.out.println(maxLen);

  }
}
