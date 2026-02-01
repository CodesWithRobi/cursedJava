class Mass { // Original
  Mass celebrate(){ return new Mass(); };
}

class TLM extends Mass { //Subtype
  TLM celebrate(){ return new TLM(); };
  //Covarient Return Type!!
}


class A {
  Object method() { return "123"; }
}

class B extends A {
  @Override
  String method() { return "213";} // String IS-A Object, other way around is illegal!
}

void main() {
  A obj = new B();
  IO.print(obj.method());
}
