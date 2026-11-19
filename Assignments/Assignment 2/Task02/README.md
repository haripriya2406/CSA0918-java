# Q2 – ArrayList, LinkedList and ArrayDeque

Run `javac PlaylistBenchmark.java` then `java PlaylistBenchmark`. Three sizes are tested for append, front insertion, middle removal, middle indexed access, full iteration and deque end operations.

Expected complexity discussion: ArrayList append is amortized O(1), front/middle insertion or removal O(n), indexed get O(1), iteration O(n). LinkedList end insertion/removal is O(1), but indexed access and locating a middle position are O(n); iteration is O(n). ArrayDeque end operations are amortized O(1), but it has no indexed get and is appropriate for the Up Next double-ended queue.

The timings are single-run measurements for exploration; repeat runs, average them, control warm-up and record hardware/JDK details for the report as the PDF requires.
