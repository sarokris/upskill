import java.util.concurrent.CompletableFuture;

public class CompletableFutureAsyncDemo {
    public static void main(String[] args) {
        System.out.println("Main thread: " + Thread.currentThread().getName());

        // Task 1: Simulate a time-consuming operation
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 1 running on: " + Thread.currentThread().getName());
            sleep(2000); // Simulate 2 seconds delay
            System.out.println("Task 1 completed!");
        });

        // Task 2: Simulate another time-consuming operation
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 2 running on: " + Thread.currentThread().getName());
            sleep(1000); // Simulate 1 second delay
            System.out.println("Task 2 completed!");
        });

        // Wait for both tasks to complete
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2);

        allTasks.join(); // Block the main thread until all tasks are complete
        System.out.println("All tasks completed!");
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
