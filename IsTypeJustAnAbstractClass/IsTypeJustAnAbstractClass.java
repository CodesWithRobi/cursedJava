interface FakeType {
  public void fakeMethod();
}

class True implements FakeType{
  public void fakeMethod() {
    System.out.println("Yo what the dog doin!!?");
  }
  public void canYouDoThis() {
    System.out.println("WATCH ME BOI!");
  }
}

public class IsTypeJustAnAbstractClass {
  public static void main(String[] args) {
    FakeType fakeyfakey = new True();
    fakeyfakey.fakeMethod();
    fakeyfakey.canYouDoThis();
  }
}
