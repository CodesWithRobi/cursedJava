void main() {
  var li = List.of(1, 2, 3);
  //Limit to 10 keys,value pair
  var map = Map.of(1, "A", 2, "B");

  var e1 = Map.entry(1, "A");
  var e2 = Map.entry(2, "B");
  //limitless
  var map2 = Map.ofEntries(e1, e2);
}
