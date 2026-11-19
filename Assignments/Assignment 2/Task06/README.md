# Q6 – Hospital Emergency Triage

Run `javac Triage.java` then `java Triage`.

Expected output:

```text
Meena(S1) Kumar(S1) Lata(S2) Ravi(S3) John(S3) Asha(S5)
```

PriorityQueue orders lower severity numbers first. The monotonically increasing arrival counter is the second comparator key, so equal-severity patients are treated FIFO. Adding and polling are O(log n); peeking is O(1). Printing the PriorityQueue directly does not guarantee sorted iteration order; repeatedly poll a copy to display treatment order.

Discuss starvation of minor cases and a fair aging rule, such as promoting a patient after a defined waiting duration. The counter gives stable tie-breaking but does not by itself prevent starvation.
