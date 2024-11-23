## CompletableFuture

A powerful framework for asynchronous programming. It allows you to create and compose tasks, handle results, and manage concurrency without blocking threads.

### Demo

> To demonstrate that CompletableFuture in Java performs tasks asynchronously, we can create a simple example where multiple tasks run independently and asynchronously, and their execution order is not guaranteed.

You can refer this [example](src/CompletableFutureAsyncDemo.java)

``` plaintext 
Main thread: main
Task 1 running on: ForkJoinPool.commonPool-worker-1
Task 2 running on: ForkJoinPool.commonPool-worker-2
Task 2 completed!
Task 1 completed!
All tasks completed!
```

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

`thenCompose`

> Chains two dependent asynchronous tasks. The second task takes the result of the first as input.

- **Use Case**: When one asynchronous computation depends on the result of another.

``` java
import java.util.concurrent.CompletableFuture;

public class ThenComposeExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "UserID123")
                .thenCompose(userId -> fetchUserDetails(userId))
                .thenAccept(details -> System.out.println("User Details: " + details));
    }

    private static CompletableFuture<String> fetchUserDetails(String userId) {
        return CompletableFuture.supplyAsync(() -> "Details for " + userId);
    }
}
``` 

`thenCombine`

> Combines two independent CompletableFuture results into one.

- **Use Case** : When two computations are independent but you need both results.

``` java
import java.util.concurrent.CompletableFuture;

public class ThenCombineExample {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World!");

        future1.thenCombine(future2, (result1, result2) -> result1 + " " + result2)
                .thenAccept(combinedResult -> System.out.println("Combined: " + combinedResult));
    }
}
``` 

