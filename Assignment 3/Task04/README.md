# Task 04 – Thread Creation and the Thread Life Cycle (7 Marks)

**Bloom's level:** K4 – Analyse

Simulate four scanner stations processing answer-script batches. Implement once by extending Thread and once using Runnable. Use start(), sleep(), join() and isAlive(); repeat runs and record finish order.

**Student A:** Compare Thread and Runnable, draw the thread life-cycle state diagram, demonstrate run() versus start(), and explain join().

**Student B:** Record finish order over at least ten runs; test priorities; make one station a daemon and record behaviour when main ends; explain scheduler observations.

**Joint:** Explain nondeterministic output order and why correctness must not depend on it.

**Alternative:** Use a fixed ExecutorService pool; analyse expected work distribution for n scripts and p threads and per-thread stack memory.

**Add:** Java source files, run logs for ten runs, state diagram, priority/daemon observations and output screenshots.
