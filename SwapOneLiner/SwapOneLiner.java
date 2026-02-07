void main() {
  int a = 7;
  int b = 6;

  a = a + b - (b = a);
  IO.println(a + "" + b);

  a = a^b^(b = a);
  IO.println(b + "" + a);

}
