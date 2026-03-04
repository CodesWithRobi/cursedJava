import java.util.regex.*;

void main() {
  String line = "This order was placed for QT3000! OK?"; 

  Pattern p1 = Pattern.compile("(.*)(\\d+)(.*)");
  Matcher m = p1.matcher(line);
  StringBuilder sb = new StringBuilder("wat");
  System.out.println(m.appendTail(sb));
}
