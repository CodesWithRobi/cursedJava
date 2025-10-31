import java.util.HashMap;

void main() {
  var hash = new HashMap<Integer, String>();
  var li = Arrays.asList("One", "Two", "Three");
  for(int i = 0; i < 3; i++) {
    hash.put(i+1, li.get(i));
  }
  var eSet = hash.entrySet();
  IO.println(eSet);

  // Not a copy but reference!!
  hash.remove(2);
  IO.println(eSet);
}
