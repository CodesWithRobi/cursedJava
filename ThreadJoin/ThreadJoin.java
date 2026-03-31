public class ThreadJoin {
    public static void main(String[] args) { // <-- Thread A (The Manager)
        
        Thread worker = new Thread(() -> {   // <-- Thread B (The Worker)
            System.out.println("Worker: Processing data for 3 seconds...");
            try { Thread.sleep(3000); } catch (Exception e) {}
            System.out.println("Worker: Done!");
        });

        worker.start(); // Both threads are now running simultaneously!

        System.out.println("Manager: Waiting for the worker to finish...");
        
        try {
            // THE CRITICAL LINE
            worker.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The Manager will NOT reach this line until the Worker is completely dead.
        System.out.println("Manager: The worker is done, I can continue!");
    }
}
