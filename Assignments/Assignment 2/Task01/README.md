# Q1 – Building Text Under Load

Run `javac StringBuildBenchmark.java` and `java StringBuildBenchmark`. It runs three contrasting workloads and prints five-run average timings in CSV form.

Prediction to discuss: repeated String concatenation in a loop creates intermediate immutable strings and repeatedly copies prior content. StringBuilder avoids repeated full-prefix copying; StringBuffer provides synchronized append operations and may have extra synchronization overhead. For an individual ticket use a local StringBuilder. For a shared mutable log, synchronize access or use a suitable thread-safe logging design; StringBuffer alone does not make a multi-step log operation atomic.

The program prints elapsed time only. Estimate intermediate object counts from the loop structure and measure memory separately; do not claim exact object counts from this timing benchmark. Compare String.join/StringJoiner as the alternative requested in the PDF.
