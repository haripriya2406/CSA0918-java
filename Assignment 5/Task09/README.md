# Task 09 – B4: Final Engineering Decision (5 Marks)

Implement migration from the pass file to the database using validation, batches and one transaction. Report rejected records with line numbers and reasons.

Use DatabaseMetaData to print database product/version, JDBC driver/version and tables in the schema. Explain why metadata helps when moving servers.

Evaluate text files, serialization and a relational database for 12 campuses and 100× the current number of passes. Consider concurrent counters, search, indexes, transactions, backup and multi-campus access.

**Alternative:** With 12 separate serialized files, searching all campuses may require scanning all records, O(n) total. Explain file locking/coordination and compare with a shared transactional database.

Evidence: migration program, failure report, metadata output, measurements and final recommendation.
