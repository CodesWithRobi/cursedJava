void main() {
  String wth = "bro look: " + null;
  IO.println(wth);
  //could be cuz of StringBuilder.append()
  IO.println("WOW LOOK: " + String.valueOf(null));
}
