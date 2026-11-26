# Task 01 – Handling Failure in a Bank ATM Simulation (6 Marks)

**Bloom's level:** K4 – Analyse

Implement an ATM withdrawal simulation and identify failures from invalid input, negative amounts, insufficient balance and a missing card-reader file.

**Shared work:** Classify failures; implement try, catch (including multi-catch) and finally; test valid and invalid inputs.

**Student A:** Relevant Throwable hierarchy, failure/exception classification table, catch-block ordering and finally guarantee.

**Student B:** At least five test inputs, catch/finally trace for each, one uncaught stack trace and demonstration of unreachable catch caused by broad-before-narrow ordering.

**Joint:** Evaluate whether catching every failure with one `catch (Exception e)` is appropriate.

**Alternative:** Compare exception handling with Scanner.hasNextInt() and error codes; discuss readability and time/space costs.

**Add:** ATM Java source, test data, exception table, stack trace, output screenshots and analysis.
