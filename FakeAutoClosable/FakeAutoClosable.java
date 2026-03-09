class FakeAutoClosable implements AutoCloseable {
  String saint;
  public FakeAutoClosable(String saint) {
    this.saint = saint;
  }
  @Override
  public void close() throws Exception {
    System.out.println(saint + " Ora Pro Nobis!");
  }
}

void main() throws Exception {
  try(var michael = new FakeAutoClosable("St.Michael the Archangel");
      var therese = new FakeAutoClosable("St.Therese de Liseux");
      var carlo = new FakeAutoClosable("St.Carlo Acutis");) {
    IO.println("BY THE INTERCESSION OF:");
      }
  catch(Exception e) {
    throw e;
  }
}
