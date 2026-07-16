public boolean isValid(String code) {
  int i = 0;
  int N = code.length();
  Deque<String> stack = new ArrayDeque<>();

  while(i < N) {
    if(i > 0 && stack.isEmpty()) return false;
    else if(code.startsWith("<![CDATA[", i)) {
      if(stack.isEmpty()) return false;
      int end = code.indexOf("]]>", i+9);
      if(end == -1) return false;
      i = end + 3;
    }
    else if(code.startsWith("</", i)) {
      if(stack.isEmpty()) return false;
      int end = code.indexOf('>', i+2);
      if(end == -1) return false;
      String tag = code.substring(i+2, end);
      if(!tag.equals(stack.pop())) return false;
      i = end + 1;
    }
    else if(code.charAt(i) == '<') {
      int end = code.indexOf('>', i+1);
      if(end == -1) return false;
      String tag = code.substring(i+1, end);
      if(tag.length() < 1 || tag.length() > 9) return false;
      stack.push(tag);
      i = end + 1;
    }
    else i++; 
  }
  return stack.isEmpty();
}

void main() {
  IO.println(isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
}
