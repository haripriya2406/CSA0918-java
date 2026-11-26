# Task 05 – Race Conditions in a Cinema Seat-Booking System (6 Marks)

**Bloom's level:** K4 – Analyse

Implement seat booking without synchronization, with a synchronized method and with a synchronized block. Test concurrent users and verify that synchronized versions do not double-book.

**Student A:** Reproduce double booking, record counts over repeated runs, show a two-thread interleaving and identify the critical section.

**Student B:** Measure 10,000 bookings for each version; compare whole-method and critical-section locking; explain static synchronization and intrinsic locks.

**Complete the Code:** Implement the check-then-book operation atomically in SeatBooking. First test the unsynchronized version over ten runs, then add synchronization and verify `Seats sold: 10`.

**Joint:** Choose and defend a synchronization strategy for a cinema with 20 screens.

**Alternative:** Compare AtomicBoolean[] with compareAndSet(), ReentrantLock and ConcurrentHashMap.putIfAbsent(); analyse contention time and additional space for s seats.

**Add:** Java source files, repeated-run results, timing table, output screenshots and analysis.
