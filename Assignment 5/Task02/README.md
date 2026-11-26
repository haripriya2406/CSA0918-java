# Task 02 – A2: Buffered I/O Design (5 Marks)

Predict the number of read calls when a 50 MB file is read one byte at a time. Redesign the counter using BufferedInputStream or a byte-array buffer. Count newline bytes and handle a final line without a trailing newline.

**Alternative:** Compare streaming with Files.readAllLines(). Both take O(n) time. Buffered streaming uses O(buffer size) extra memory; readAllLines uses O(file size) memory plus line-object overhead. Explain when buffering gives little benefit.

Evidence: unbuffered and buffered code, measurements for 1 MB/10 MB/50 MB, and interpretation.
