# Task 01 – A1: Design a File Format (7 Marks)

Design the bus-pass record in two formats:
1. Delimited text: pass ID, register number, name, route, validity date and amount.
2. Binary DataOutputStream: document field order, numeric types and how variable-length strings are encoded.

Write SIMATS-standard pseudocode for add, find by pass ID and list by route. Justify the stream classes used. Explain handling of damaged lines, missing files and incomplete writes.

**Alternative:** Compare an O(n) text scan with fixed-length RandomAccessFile seeking. Seeking is O(1) only when the record index is already known; finding an ID without an index still needs O(n) scanning. Explain padding waste.

Evidence: both layouts, three algorithms, error handling and complexity analysis.
