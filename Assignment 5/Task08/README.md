# Task 08 – B3: Statement vs PreparedStatement vs Batch (8 Marks)

Create a MySQL bus_pass table. Insert 10 rows using Statement, PreparedStatement and PreparedStatement batch. Verify with SELECT and ResultSetMetaData. Measure 1,000, 10,000 and 100,000 rows for all approaches.

Complete PassLoader TODOs:
- Bind five parameters, addBatch and execute each 1,000 rows.
- Commit and return the loaded row count.
- Roll back and rethrow on SQLException or RuntimeException.
- Restore the original auto-commit setting.

Inject an invalid amount halfway through and verify rollback leaves no partial rows.

**Alternative:** Compare PreparedStatement batches with multi-row INSERT and LOAD DATA. Total row processing is O(n); discuss network round trips and memory O(b) for a batch of b rows. Measure rather than invent timings.

Evidence: SQL script, completed code, metadata output, timings and rollback proof.
