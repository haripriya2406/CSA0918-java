# Assignment 2 – CO2: Java Strings and Collections

**Coverage:** Strings/StringBuilder/StringBuffer, regular expressions, List/Queue/Deque/PriorityQueue/Set/Map, equals–hashCode, Generics, Comparable/Comparator and Collections utilities.

**Java target:** JDK 8+

This folder follows the uploaded 12-page Assignment 2 PDF. Each question has its own folder and runnable source file. The PDF requires individual execution, progressive commits, output screenshots, raw measurements and a report. The benchmark programs below generate measurements on the machine where they are run; no timing results have been invented here.

## Tasks and programs

| Question | Folder | Program | Focus |
|---|---|---|---|
| Q1 | Task01 | StringBuildBenchmark.java | String, StringBuilder and StringBuffer workloads |
| Q2 | Task02 | PlaylistBenchmark.java | ArrayList, LinkedList and ArrayDeque operations |
| Q3 | Task03 | Attendance.java | equals/hashCode contract and mutable-key warning |
| Q4 | Task04 | PharmacyMaps.java | HashMap, TreeMap and LinkedHashMap |
| Q5 | Task05 | LeaderboardDemo.java | Generic leaderboard and comparator chaining |
| Q6 | Task06 | Triage.java | PriorityQueue with arrival-order tie-break |
| Q7 | Task07 | RedesignDemo.java | Diagnose front-insertion bottleneck and validate deque redesign |

## Compile and run

Open a terminal in the relevant task folder, then run:

```bash
javac FileName.java
java MainClassName
```

Use the file and class names shown in each task README. Run benchmark programs several times and retain the generated CSV files as raw evidence. Record machine/OS/JDK details and use the same conditions for original and redesigned implementations.

## Evidence still to be completed by the student

The assignment asks for predictions before execution, repeated averaged measurements, memory/object estimates, analysis, output screenshots, a written report and a viva-ready explanation. Benchmark output depends on the student's machine and must be collected locally; do not present sample or estimated timings as actual measurements.
