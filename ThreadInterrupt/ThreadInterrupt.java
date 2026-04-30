void main() {
  Thread Task = new Thread(() -> {
    for(int i = 0; i < 5; i++) {
      IO.println("[" + Thread.currentThread().getName() + "] Message: " + i);
      try {
        IO.println("Before sleep: " + Thread.currentThread().isInterrupted());
        Thread.sleep(Duration.ofSeconds(2));
        IO.println("After sleep: " + Thread.currentThread().isInterrupted());
      } catch (InterruptedException e) { //Reset the interrupted to false
        IO.println("This thread has been interrupted, status: " + Thread.currentThread().isInterrupted());
      }
    }
  });
  Task.start();
  Task.interrupt();
}
