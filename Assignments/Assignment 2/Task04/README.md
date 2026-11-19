# Q4 – Pharmacy Inventory Maps

Run `javac PharmacyMaps.java` then `java PharmacyMaps`. The program populates three map implementations at three sizes, times insertion, 100,000 lookups and iteration, then demonstrates TreeMap key order and LinkedHashMap insertion order.

Expected properties: HashMap put/get are O(1) average and iteration O(n), with no ordering guarantee. TreeMap put/get are O(log n), iteration O(n), and keys are sorted. LinkedHashMap put/get are O(1) average and iteration O(n), preserving insertion order. A HashMap report needing sorted keys requires sorting keys or copying to TreeMap. Hashtable is legacy synchronized collection; modern code usually chooses a suitable Map and explicit concurrency strategy.

The lookup loop is a repeatable demonstration, but benchmark numbers are machine/JVM-dependent. Define your acceptable lookup threshold and justify it using your own repeated measurements. Compare with a sorted array plus Arrays.binarySearch as requested.
