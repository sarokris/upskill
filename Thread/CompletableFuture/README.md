## CompletableFuture

A powerful framework for asynchronous programming. It allows you to create and compose tasks, handle results, and manage concurrency without blocking threads.

### Key Methods and Examples
`thenAccept`
- **Use Case**: Perform an action using the result, without returning a new future.

``` java

import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello, World!")
                .thenAccept(result -> System.out.println("Received: " + result));
    }
}
```

`thenApply`
- Transforms the result of a CompletableFuture.
- **Use Case**: Apply a function to the result and return a new transformed result.

``` java
import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Welcome to Async programming");
        future.thenApply(x -> x + " in Java").thenAccept(System.out::print);
    }
}
```