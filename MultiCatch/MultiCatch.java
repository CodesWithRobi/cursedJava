void main() {
  try {
    int[] arr = new int[2];
    arr[0] /= arr[1];
  } catch(ArrayIndexOutOfBoundsException | ArithmeticException e) {
    System.out.println("Runtime Exception: " + e);
  } catch(Exception e) {
    System.out.println("The exception is " + e);
    e.printStackTrace();
  }
}
