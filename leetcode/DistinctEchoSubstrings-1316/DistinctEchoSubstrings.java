import java.util.HashSet;

void main() {
  String text = "leetcodeleetcode";
  Set<String> set = new HashSet<>();
  for(int i = 0; i < text.length()-1; i++) {
    for(int j = i+1; j < text.length(); j+=2) {
      int mid = (i+j+1)/2;
      if(text.substring(i, mid).equals(text.substring(mid, j+1))) {
        set.add(text.substring(i, j+1));
      }
    }
  }
  IO.println(set.size());
}
