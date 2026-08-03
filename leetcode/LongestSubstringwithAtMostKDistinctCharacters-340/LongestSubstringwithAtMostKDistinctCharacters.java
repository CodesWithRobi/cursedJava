void main() {
  IO.println("Output 1: " + lengthOfLongestSubstringKDistinct("eceba", 2));
  IO.println("Output 2: " + lengthOfLongestSubstringKDistinct("aa", 1));

}

public int lengthOfLongestSubstringKDistinct(String s, int k) {
  if(s == null || s.length() == 0 || k == 0) return 0;
  int l = 0, r = 0, n = s.length();
  Map<Character, Integer> map = new LinkedHashMap<>(k+1, 0.75f, true);

  int max = 1;
  while(r < n) {
    char ch = s.charAt(r);
    map.put(ch, r);
    if(map.size() == k+1) {
      max = Math.max(max, r-l);
      Character old = map.keySet().iterator().next();
      Integer pos = map.remove(old);
      l = pos+1;
    }
    r++;
  }
  return max = Math.max(max, r-l);
}
