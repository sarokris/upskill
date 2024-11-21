import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Welcome to Async programming");
        future.thenApply(x -> x + " in Java").thenAccept(System.out::print);
    }
}
