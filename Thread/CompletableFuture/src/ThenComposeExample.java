import java.util.concurrent.CompletableFuture;

public class ThenComposeExample {
    
        public static void main(String[] args) {
            System.out.println("Main thread: " + Thread.currentThread().getName());
    
        // Sequential execution
        long sequentialStart = System.currentTimeMillis();
        String user = fetchUserById(1);
        String processedUser = processUser(user);
        System.out.println("Result (Sequential): " + processedUser);
        long sequentialEnd = System.currentTimeMillis();
        System.out.println("Time taken (Sequential): " + (sequentialEnd - sequentialStart) + " ms\n");

        // Asynchronous execution
        long asyncStart = System.currentTimeMillis();
        CompletableFuture<String> asyncResult = CompletableFuture.supplyAsync(() -> fetchUserById(1))
                .thenCompose(userResult -> CompletableFuture.supplyAsync(() -> processUser(userResult)));
        asyncResult.thenAccept(result -> System.out.println("Result (Async): " + result)).join();
        long asyncEnd = System.currentTimeMillis();
        System.out.println("Time taken (Async): " + (asyncEnd - asyncStart) + " ms");
        }
    
        
        private static String fetchUserById(int userId) {
            System.out.println("Fetching user in thread: " + Thread.currentThread().getName());
            sleep(2000); // Simulate a 1-second delay
            return "User" + userId;
        }
    
        // Simulate an async operation to process user data
        private static String processUser(String user) {
                System.out.println("Processing user in thread: " + Thread.currentThread().getName());
                sleep(500); // Simulate a 1.5-second delay
                return "Processed " + user;
        }
    
        // Helper method to simulate delay
        private static void sleep(long milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
