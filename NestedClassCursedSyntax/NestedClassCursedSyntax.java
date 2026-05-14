class A {
  class AA extends A{
    void main() {
      A.this.print(); // Like wow refer Default Method they did similar in java 8
    }
  }
  void print() {
    IO.println("So cursed T^T");
  }
}
