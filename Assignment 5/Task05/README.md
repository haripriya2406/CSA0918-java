# Task 05 – A5: Transactions and SQL Injection (4 Marks)

Write pseudocode for transferring ₹500 between hostel and canteen wallets using a single JDBC transaction: disable auto-commit, perform both updates, commit only if both succeed, otherwise rollback, and restore the original auto-commit setting.

Analyse the unsafe query formed by concatenating ID and password. Demonstrate the crafted password ' OR '1'='1 and explain why it can make the SQL condition true.

Redesign with PreparedStatement placeholders and setString(). In a real application, use salted password hashes rather than plaintext passwords.

Evidence: transaction pseudocode, injection explanation, safe code and auto-commit comparison.
