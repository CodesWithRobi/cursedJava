class FastException extends RuntimeException{

  @Override
  public Throwable fillInStackTrace() {
    //bypassing the native C++ code that walks the thread stack saving massive amounts of CPU cycles
    //but since the Throwable() would be calling to fill the stack trace this is only then be used as flag to execute some code..
    return this;
  }
}

void main() {
  try {
    throw new RuntimeException();
  } catch(Exception e) {
    System.out.println("Normal Exception:");
    e.printStackTrace();
    e.getLocalizedMessage()
  }
  try {
    throw new FastException();
  } catch(Exception e) {
    System.out.println("-".repeat(50) + "\nFast Exception:");
    e.printStackTrace();
  }
}
