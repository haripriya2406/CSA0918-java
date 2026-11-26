# Task 03 – Resource Safety, Debugging and Robust Code (7 Marks)

**Bloom's level:** K4 – Analyse

Build a daily sales report generator that reads several store files and writes a summary. Implement a manual try-catch-finally/close() version and a try-with-resources version, then compare their reports.

**Student A:** Debug the manual version, reproduce a partial failure, count leaked/open files in both versions and log the file that failed.

**Student B:** Identify resources and closing points; explain leaks, automatic close order and suppressed exceptions.

**Joint:** State when try-with-resources alone is insufficient for robustness.

**Alternative:** Compare try-with-resources, explicit close in finally and no closing. For f store files, analyse processing time and maximum simultaneously open file handles.

**Add:** Java source files, sample store inputs, debugger evidence, logs, open-resource counts, output screenshots and analysis.
