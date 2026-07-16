import java.security.KeyStore.Entry;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.TreeMap;

String countOfAtoms(String formula) {
  Deque<Map<String, Integer>> stack = new ArrayDeque<>();
  stack.push(new HashMap<>());
  char[] ch = formula.toCharArray();
  for(int i = 0; i < ch.length; i++) {
    if(ch[i] == '(') stack.push(new HashMap<>());
    else if(ch[i] == ')') {
      if(i+1 < ch.length && Character.isDigit(ch[i+1])) {
        int val = ch[i+1]-'0';
        stack.peek().replaceAll((k, v) -> v*val);
      }
      var mergeHash = stack.pop();
      var headHash = stack.peek();
      mergeHash.forEach((k, v) -> {
        headHash.merge(k, v, Integer::sum);
      });
    }
    else if(Character.isUpperCase(ch[i])) {
      var hash = stack.peek();
      String chStr = Character.toString(ch[i]);
      if(ch.length == i+1 || Character.isUpperCase(ch[i+1]) || ch[i+1] == '(' || ch[i+1] == ')') {
        hash.put(chStr, hash.getOrDefault(chStr, 0)+1);
      }
      else if(Character.isDigit(ch[i+1])) {
        int digitEnd = i+2;
        while(Character.isDigit(ch[digitEnd++]));
        hash.put(chStr, hash.getOrDefault(chStr, 0)+Integer.parseInt(formula.substring(i+1, digitEnd)));
        i = digitEnd;
      }
      else if(Character.isLowerCase(ch[i+1])) {
        chStr += ch[i+1];
        i++;
        hash.put(chStr, hash.getOrDefault(chStr, 0)+ (Character.isDigit(ch[i+2]) ? (ch[i+2]-'0') : 1));
      }
    }
  }
  TreeMap<String, Integer> tm = new TreeMap<>(stack.pop());
  StringBuilder sb = new StringBuilder();
  tm.forEach((k, v) -> sb.append(v == 1 ? k : k + v));
  return sb.toString();
}

void main() {
  // IO.println(countOfAtoms("K4(ON(SO3)2)2"));
  IO.println(countOfAtoms("Be342"));
}
