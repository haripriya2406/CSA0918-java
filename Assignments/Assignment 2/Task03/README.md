# Q3 – hashCode–equals Contract

Run `javac Attendance.java` then `java Attendance`. Expected first line: `Unique students: 2`.

The completed class compares students by registration number and derives hashCode from that same identity field. The PDF asks you to first run the supplied faulty version (equals without hashCode), record its output, then add hashCode and compare. This repository contains the corrected version; create a separate temporary copy without hashCode to capture your before-fix result. The precise faulty output can vary because hash bucket placement is implementation-dependent.

The identity field is final to avoid mutating a key after insertion into a hash-based collection. Also investigate TreeSet with a comparator on regNo and HashMap keyed by regNo as the alternative designs.
