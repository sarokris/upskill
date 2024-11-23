import java.util.concurrent.CompletableFuture;

public class ThenCombineExample {
    public static void main(String[] args) {
        System.out.println("Comparing Sequential vs Asynchronous Execution with thenCombine:");

        // Sequential Execution
        long sequentialStart = System.currentTimeMillis();
        String userDetails = fetchUserDetails(1);
        String orderDetails = fetchOrderDetails(1);
        String resultSequential = combineUserAndOrder(userDetails, orderDetails);
        System.out.println("Result (Sequential): " + resultSequential);
        long sequentialEnd = System.currentTimeMillis();
        System.out.println("Time taken (Sequential): " + (sequentialEnd - sequentialStart) + " ms\n");

        // Asynchronous Execution with thenCombine
        long asyncStart = System.currentTimeMillis();
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> fetchUserDetails(1));
        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> fetchOrderDetails(1));

        CompletableFuture<String> combinedFuture = userFuture.thenCombine(orderFuture, ThenCombineExample::combineUserAndOrder);

        combinedFuture.thenAccept(resultAsync -> System.out.println("Result (Async): " + resultAsync)).join();
        long asyncEnd = System.currentTimeMillis();
        System.out.println("Time taken (Async): " + (asyncEnd - asyncStart) + " ms");
    }

    // Mock method to fetch user details (simulates delay)
    private static String fetchUserDetails(int userId) {
        sleep(500); // Simulate 2 seconds delay
        return "UserDetails for User" + userId;
    }

    // Mock method to fetch order details (simulates delay)
    private static String fetchOrderDetails(int userId) {
        sleep(500); // Simulate 3 seconds delay
        return "OrderDetails for User" + userId;
    }

    // Combines user and order details into a single result
    private static String combineUserAndOrder(String userDetails, String orderDetails) {
        return userDetails + " | " + orderDetails;
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
