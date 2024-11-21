import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample{
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Welcome to Async programming");
        future.thenAccept(System.out::print);
    }
}