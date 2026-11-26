# Task 02 – User-Defined Exceptions and Propagation (7 Marks)

**Bloom's level:** K4 – Analyse

Design an exception hierarchy for an online examination portal, covering closed exam windows, unregistered students and oversized answer files. Implement propagation across controller, service and storage layers and preserve causes through exception chaining.

**Student A:** Exception class diagram, checked/unchecked justification, propagation path and final user-facing message.

**Student B:** One test scenario per exception, getCause() and chained stack trace, comparison with rethrowing without a cause, and test table.

**Complete the Code:** Finish TODO 1 in StorageDemo by throwing a SubmissionStorageException with message `Could not store ` + fileName and the caught IOException as its cause. Expected output is in the assignment handout.

**Joint:** Explain why storage-specific exceptions are preferable to exposing raw IOException to the UI.

**Alternative:** Compare checked exceptions with RuntimeException-based unchecked exceptions; analyse stack-trace and cause-chain memory costs.

**Add:** Java source files, class diagram, test table, stack trace, output screenshot and analysis.
