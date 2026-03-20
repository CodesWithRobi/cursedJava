void main() {
  try {
    // System.exit(1); // If exit method called first then finally won't run
    return;
  } finally {
    IO.println("Finally Runs!!");
    //So powerful that it must run
  }
}
