void transubstantiation() {
  this.bread = "Body";
  this.wine = "Blood";
}

String bread = "Bread";
String wine = "Wine";

void main() {
  IO.println(bread + " & " + wine);
  transubstantiation();
  IO.println(bread + " & " + wine);
}
