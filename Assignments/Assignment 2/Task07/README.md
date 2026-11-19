# Q7 – Diagnose, Redesign and Validate

This implementation selects the playlist Up Next queue as the redesign case. The original ArrayList performs element shifting for front insertion, so repeated front operations can take O(n) each. ArrayDeque uses a resizable circular array and supports amortized O(1) insertion/removal at both ends.

Run `javac RedesignDemo.java` then `java RedesignDemo`. It compares the same sequence of front-add/opposite-end-remove operations at three sizes and prints local timings.

Before running, predict that ArrayDeque scales better for this access pattern. Record repeated runs on the same machine/JDK, compare averages, and include the raw output in your report. Discuss the trade-off: ArrayDeque has no indexed access and is not a replacement for ArrayList when frequent random access is required. Do not claim improvement until your own measurements support it.
