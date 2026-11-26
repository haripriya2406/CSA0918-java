# Task 06 – Deadlock and Inter-Thread Communication (7 Marks)

**Bloom's level:** K4 – Analyse

Part A: Implement hostel wallet transfers that can deadlock, then prevent deadlock using consistent lock ordering. Part B: Implement a bounded producer-consumer mess token counter with capacity five using wait(), notify() and notifyAll().

**Student A:** Explain why wait() belongs in a synchronized block and a loop; investigate notify() versus notifyAll(); record buffer bounds; compare sleep() and wait().

**Student B:** Construct a reliable deadlock, identify the four necessary conditions, prevent it with lock ordering and explain why the rule works.

**Complete the Code:** Complete TODO 1 and TODO 2 in TokenCounter. Use while loops, wait while full/empty, insert/remove circularly, update count, call notifyAll(), and return the removed token. Verify sum 210. Then replace while with if and explain the difference.

**Joint:** Explain progress guarantees in deadlock prevention and producer-consumer coordination.

**Alternative:** Compare wait/notifyAll with ArrayBlockingQueue<Integer> and lock ordering with timed tryLock; analyse time and space.

**Add:** Java source files, deadlock evidence, buffer-size logs, test outputs and analysis.
