# Task 06 – B1: Implement and Instrument the File Store (7 Marks)

Complete PassStore.find() using BufferedReader in try-with-resources. Read line by line, skip records with other than five fields, return the matching record, and return null when absent.

Suggested core:
```java
try (BufferedReader r = Files.newBufferedReader(file)) {
    String line;
    while ((line = r.readLine()) != null) {
        String[] fields = line.split(",", -1);
        if (fields.length != 5) continue;
        if (fields[0].equals(passId)) return fields;
    }
}
return null;
```

**Alternative:** q scans over n records cost O(q × n). Loading once into a HashMap costs expected O(n), then q lookups cost expected O(q), total O(n + q), using O(n) extra memory.

Evidence: complete code, normal/missing/damaged tests, buffering measurements for 1/10/50 MB, search timings for 1,000–100,000 records and interpretation.
