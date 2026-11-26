# Q7 – Concurrent, Fault-Tolerant Semester Result Processor

## Requirements and measurable checks

- Process 10,000 expected file paths.
- Validate each file independently; missing, corrupt, malformed and out-of-range records count as failures.
- One failed file must not terminate processing of other files.
- At completion, successes + failures must equal 10,000.
- Compare elapsed time with fixed pools of 1, 2, 4 and 8 workers.

## Design

**Classes:** `ResultProcessor` coordinates the run; `ProcessFile` implements `Callable<FileResult>` for one file; `FileResult` stores the file name, status and message.

**Shared data:** a `ConcurrentLinkedQueue<FileResult>` stores outcomes safely from worker threads. Each task owns its local parsing variables. The expected file list and Future list are created by the coordinator.

**Failure policy:** file-level I/O, parse and validation failures become a failed `FileResult`. An unexpected exception surfaced by `Future.get()` is also counted as a failure, and collection continues.

## Pseudocode

```text
create 10,000 expected file paths
for poolSize in [1, 2, 4, 8]:
    create fixed ExecutorService(poolSize)
    create concurrent result queue
    submit one Callable per expected file
    for each Future:
        get result; if task unexpectedly failed, record failure
    shutdown executor
    count successes and failures
    print total and elapsed time
```

## Class diagram

```text
+--------------------------+
|      ResultProcessor     |
|--------------------------|
| + main()                 |
| + createSampleFiles()    |
| + runWithPool()          |
+------------+-------------+
             | submits
             v
+--------------------------+
|       ProcessFile        |
|--------------------------|
| - file: Path             |
| + call(): FileResult     |
+------------+-------------+
             | creates
             v
+--------------------------+
|        FileResult        |
|--------------------------|
| + fileName               |
| + success                |
| + message                |
+--------------------------+
```

## Implementation

The complete Java 8 implementation is in `ResultProcessor.java`. It creates sample data: every 100th expected file is deliberately missing, every 97th file is corrupt, and the remaining files contain a student ID and mark. It then runs the same workload with pools of 1, 2, 4 and 8.

Compile and run:

```bash
javac ResultProcessor.java
java ResultProcessor
```

The sample has 10,000 expected paths. 100 are missing. Of the 103 indices divisible by 97, one (9700) is also a missing-file index and is skipped; therefore 102 corrupt files are written. Expected totals: **202 failures and 9,798 successes**. The final check must show 10,000 total for every pool size. These are expected counts, not measured timings.

## Performance and complexity

Let N be the number of files, L the average bytes/records per file, and P the worker count.

- Total work is O(N × L), because each file is read and validated once.
- Ideal CPU-bound wall time is approximately O(N × L / P), but disk I/O, scheduling, contention and storage bandwidth limit scaling.
- The result queue and Future list use O(N) space; the thread pool uses O(P) worker threads plus their stack memory. File contents are read one at a time per task.
- Choose the recommended pool size from measurements on the test machine. More threads may be slower when disk I/O is saturated or scheduling overhead dominates.

## Alternatives

- `files.parallelStream()` uses the common ForkJoinPool by default. It avoids creating 10,000 dedicated threads, but offers less direct control over pool configuration. Catch exceptions inside each per-file operation and convert them to result records; otherwise an exception may escape the terminal operation and fail the overall stream operation.
- One thread per file creates 10,000 threads. Approximate stack reservation is 10,000 × configured thread stack size, in addition to thread objects and task data. This can exhaust memory and cause heavy context switching. A failure must be caught inside each thread and placed into a thread-safe result collection; uncaught failures do not automatically become a complete summary.
- A bounded ExecutorService is usually the clearest choice: it limits concurrency, makes pool sizing explicit, and provides Futures for collecting task completion and failures.

## Verification table

Fill this with the actual output from your computer:

| Pool size | Success | Failure | Total | Time (ms) |
|---:|---:|---:|---:|---:|
| 1 | 9,798 | 202 | 10,000 | |
| 2 | 9,798 | 202 | 10,000 | |
| 4 | 9,798 | 202 | 10,000 | |
| 8 | 9,798 | 202 | 10,000 | |

## Limitation

This teaching implementation stores 10,000 Future objects and all results in memory. For much larger workloads, process files in batches and persist summary records to a database or output file. Sample input files are generated locally and timings depend on the machine and storage device.
