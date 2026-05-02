import java.util.concurrent.locks.ReentrantLock;

class Thrd {
   // creating object of ReentrantLock class
   private static ReentrantLock lockr = new ReentrantLock(true); //by default no fair, so this make sure to include how long it wait
   static void operation(int data) {
     // give access to lock
     boolean lockAcquired = lockr.tryLock(); // instant and not fair, returns a boolean. It returns true if you got the lock, and false if someone else had it. 
     if (lockAcquired) {
       try {
         lockr.lock(); // The thread guarantees it will eventually get in, but it gives up control of time.
         // giving lock to thread
         for(int i = 1; i <= 4; i++) {
            System.out.println(data++);
         }
         // checking lock count
         System.out.println("Count of Lock: " + lockr.getHoldCount());
       } finally {
         lockr.unlock(); 
         // unlocking the lock 
       }
     } else {
       System.out.println("I am in else block");
     }
   }
}
class Thrd1 extends Thread {
   // thread number 1 
   public void run() {
     Thrd.operation(1);
     // method calling  
   }
}
class Thrd2 extends Thread {
   // thread number 2 
   public void run() {
     Thrd.operation(5);  
     // method calling
   }
}
class Thrd3 extends Thread {
   // thread number 3
   public void run() {
     Thrd.operation(10);  
     // method calling
   }
}
public class TestThread {
   public static void main(String args[]) {
     // creating object for thread class
     Thrd1 oprt1 = new Thrd1();
     Thrd2 oprt2 = new Thrd2();  
     Thrd3 oprt3 = new Thrd3();
     // Starting the thread operation
     oprt1.start();
     oprt2.start();  
     oprt3.start();
   }
}
