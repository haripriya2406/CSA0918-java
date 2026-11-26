# Task 01 – GUI Design Review (6 Marks)

## 1.1 Deficiencies
1. UI, validation, payment and JDBC are mixed in one event handler; this makes the window difficult to test.
2. `==` compares String references. Use `"Pay".equals(e.getActionCommand())` or separate listeners.
3. Empty/non-numeric amount causes `NumberFormatException`; negative/zero amounts and empty registration IDs are not rejected.
4. JDBC work and `Thread.sleep(3000)` run on the event thread; the window freezes and cannot repaint/respond.
5. Feedback is printed to the console instead of shown to the user.
6. Receipt button has no receipt implementation; it falls into the Clear branch.
7. SQL is built by concatenating user input, enabling SQL injection and quoting errors. Use PreparedStatement.
8. Connection and Statement are not closed; resources leak. Use try-with-resources.
9. Window closing behaviour is not defined.
10. JDBC and InterruptedException checked exceptions are not handled/declared.

## 1.2 Repaired design
- `FeeWindow`: collects fields, invokes service, shows status, clears inputs and handles close.
- `FeeService`: validates registration number and amount and performs payment.
- Database access should use PreparedStatement and try-with-resources. Long work should run in SwingWorker (for Swing) or a worker thread (AWT), not the event-dispatch thread.

## 1.3 Design defence (under 100 words)
The redesigned window handles only user interaction and presentation, while FeeService contains validation and payment rules. The service can be tested without opening a GUI by passing sample values and checking its result. UI changes do not require rewriting database logic, and payment rules can be reused by another screen. Separate responsibilities make defects easier to locate and reduce duplicated code.

## Alternative and complexity
With one shared listener, dispatch may perform up to k action-command comparisons: O(k) worst case and O(1) extra listener space. With one listener per button, the event source already identifies its registered listener, so dispatch is O(1) at the application-handler level; k listener objects require O(k) space. Real GUI framework dispatch overhead is not literally zero and should not be confused with the handler's comparison count.

## Source
See `FeeWindow.java` and `FeeService.java`.
