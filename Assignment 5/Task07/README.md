# Task 07 – B2: Engineer Adversarial Inputs (5 Marks)

Use a local test database created only for this assignment. Construct an input that makes the unsafe concatenated login query accept an invalid login. Run the same input using PreparedStatement and show it is treated as a literal value and rejected.

Create damaged bus-pass files containing a wrong field count, non-numeric amount and empty line. Explain what each test reveals.

**Alternative:** Explain why quote escaping or regex validation is weaker than parameter binding. Validation is generally O(length of input), while database query execution and database access dominate total query cost.

Evidence: SQL setup, crafted input, unsafe/safe outputs and malformed-file results.
