public class ThreadJoin {
    public static void main(String[] args) { // <-- Thread A (The Manager)
        
        Thread worker1 = new Thread(() -> {   // <-- Thread B1 (The Worker1)
            System.out.println("Worker: Processing data for 3 seconds...");
            try { Thread.sleep(3000); } catch (Exception e) {}
            System.out.println("Worker: Done!");
        });
        Thread worker2 = new Thread(() -> {   // <-- Thread B1 (The Worker1)
            System.out.println("Worker: Processing data for 3 seconds...");
            try { Thread.sleep(3000); } catch (Exception e) {}
            System.out.println("Worker: Done!");
        });

        worker1.start(); // Both threads are now running simultaneously!
        worker2.start(); // All threads are now running simultaneously!

        System.out.println("Manager: Waiting for the worker to finish...");
        
        try {
            // THE CRITICAL LINE
            worker1.join(); // Manager(main) is stoped, but worker 2 is not
            worker2.join(); // worker1 died, if worker2 hasn't finished yet that is running main is paused
            //I don't care who finishes first, but you are not allowed to move past this line until BOTH are in the grave.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The Manager will NOT reach this line until the Worker is completely dead.
        System.out.println("Manager: The worker is done, I can continue!");
    }
}
