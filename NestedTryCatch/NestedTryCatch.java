import java.io.IOException;

class CustomException extends RuntimeException {
  @Override
  public synchronized Throwable fillInStackTrace() { //The Throwable constructor call this method
    IO.println("The deed is done!");
    return this;
  }
}

void main() {
  try {
    try {
      int a = 10;
      int b = 0;
      a /= b;
    } catch (ArithmeticException e) {
      throw new CustomException();
    }
  } catch(CustomException e) {
    IO.println("YOOO THIS IS WORKING BOIII!");
  }
}
