# Task 8 – Polymorphism

Compile and run using `javac FeeDemo.java` then `java FeeDemo`.

Expected output:

```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

A `Vehicle[]` stores different subclass objects. Runtime dispatch calls each object's overridden hourly-rate method. `Chargeable` adds the EV charging capability. Code uses Java 8-compatible syntax.
