void main() {
  try {
    int[] arr = new int[2];
    arr[0] /= arr[1];
  } 
  // catch(Exception e) {                              // If you do this it won't even compile!
  //   System.out.println("The exception is " + e);    // exception already been caught!!
  //   e.printStackTrace();
  // }
  // catch(IndexOutOfBoundsException | ArrayIndexOutOfBoundsException  e) { //in the same inheritance tree
  //   System.out.println("The exception is " + e);
  //   e.printStackTrace();
  // }
  catch(ArrayIndexOutOfBoundsException | ArithmeticException e) { //not in the same inheritance tree
    System.out.println("Runtime Exception: " + e);
  } 
}
