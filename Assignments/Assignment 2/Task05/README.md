# Q5 – Generic Leaderboard

Run `javac LeaderboardDemo.java` then `java LeaderboardDemo`.

Expected output:

```text
[Eagles(9,6), Bisons(9,4), Panthers(9,4), Cobras(6,1)]
```

The generic Leaderboard accepts a Comparator<? super T>, copies its internal list and sorts the copy, so ranking() does not mutate insertion order. addAll(List<? extends T>) demonstrates a producer-bounded wildcard. The comparator orders points descending, goal difference descending, then name ascending. Bisons precedes Panthers alphabetically after the first two fields tie.

A Comparator is more reusable than baking one natural order into Team when the same ranking component must support players, colleges and other types.
