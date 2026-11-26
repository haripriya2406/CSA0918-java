# Task 07 – Design a Concurrent, Fault-Tolerant Processor from First Principles (10 Marks)

**Bloom's level:** K5 – Evaluate

Design a processor for 10,000 answer-script score files. Read and validate files concurrently, tolerate corrupt/missing files, and produce complete success/failure summaries.

**Stage 1 – Joint:** Identify parallel tasks/shared data/failure modes and measurable requirements.

**Stage 2 – Student A:** Design with ExecutorService, Callable and Future; choose a concurrent collection; isolate per-file failures; provide class diagram and pseudocode.

**Stage 3 – Student B:** Implement, inject corrupt/missing files, measure pools of 1, 2, 4 and 8 threads, verify summary counts against a hand-checked sample and record unhandled failures.

**Stage 4 – Joint:** Recommend a pool size, explain summary correctness, identify a remaining limitation and defend the design.

**Alternative:** Compare ExecutorService with parallel streams and one-thread-per-file; analyse runtime, thread-stack memory and failure handling.

**Add:** Java source files, sample score files, injected failure cases, class diagram, pseudocode, raw timing data, summary validation, graphs/screenshots and final evaluation.
