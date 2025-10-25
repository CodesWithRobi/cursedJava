void main() {
  String a = new String("hello");
  String b = new String("hello");
  String c = new String("hello").intern();
  String d = new String("hello").intern();

  IO.println(a == b);
  IO.println(c == d);
}
