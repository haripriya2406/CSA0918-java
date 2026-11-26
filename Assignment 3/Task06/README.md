# Q6 – Deadlock and Inter-Thread Communication

## Complete program

```java
public class TokenCounter {
    private final int[] buffer = new int[5];
    private int count = 0, in = 0, out = 0;

    public synchronized void put(int token) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[in] = token;
        in = (in + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int token = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        notifyAll();
        return token;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenCounter counter = new TokenCounter();
        int[] collected = new int[2];

        Thread cook = new Thread(() -> {
            try {
                for (int t = 1; t <= 20; t++) counter.put(t);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread[] students = new Thread[2];
        for (int s = 0; s < 2; s++) {
            final int id = s;
            students[s] = new Thread(() -> {
                try {
                    for (int k = 0; k < 10; k++) collected[id] += counter.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        cook.start();
        for (Thread t : students) t.start();
        cook.join();
        for (Thread t : students) t.join();

        System.out.println("Sum of tokens collected: "
                + (collected[0] + collected[1]) + " (expected 210)");
    }
}
```

## Compile and run

```bash
javac TokenCounter.java
java TokenCounter
```

Expected output:

```text
Sum of tokens collected: 210 (expected 210)
```

The cook produces tokens 1 through 20. Two student threads each consume 10 tokens. The circular array has capacity 5. The synchronized methods protect the shared array and indexes. `wait()` releases the object's monitor while the thread is waiting; `notifyAll()` wakes waiting threads after the buffer changes.

## Why while is required; what happens with if?

Use `while` to re-check the condition after waking. Java permits spurious wakeups, and another thread may consume or fill the available slot before the awakened thread reacquires the monitor. With `if`, the thread proceeds without checking again, which can cause an invalid read/write or incorrect count. It may appear to work in several runs because the race is timing-dependent; a few successful runs do not prove correctness.

To test, change both loops to `if`, compile and run repeatedly. Record the output and any exception/hang. The incorrect version is not guaranteed to fail on every run.

## Alternatives and complexity

- `ArrayBlockingQueue<Integer>(5)`: `put` blocks when full and `take` blocks when empty. Each successful queue operation is O(1) for this bounded queue; storage is O(5), i.e. O(capacity). It handles the waiting protocol internally and is generally less error-prone.
- `tryLock(timeout)` for transfers: each lock attempt is O(1), but retries and timeout/backoff add variable time. A transfer using two accounts must acquire both locks and release any acquired lock if the second acquisition fails. A consistent global lock order is often simpler to reason about. A timeout prevents indefinite waiting only if failure and retry are handled correctly.
- For producer-consumer code, prefer the standard blocking queue unless the assignment specifically requires demonstrating `wait/notifyAll`.
