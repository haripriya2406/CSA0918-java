import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultProcessor {
    static class FileResult {
        final String fileName;
        final boolean success;
        final String message;
        FileResult(String fileName, boolean success, String message) {
            this.fileName = fileName;
            this.success = success;
            this.message = message;
        }
    }

    static class ProcessFile implements Callable<FileResult> {
        private final Path file;
        ProcessFile(Path file) { this.file = file; }

        @Override
        public FileResult call() {
            try {
                List<String> lines = Files.readAllLines(file);
                if (lines.size() != 1)
                    throw new IllegalArgumentException("Expected one CSV line");

                String[] fields = lines.get(0).split(",");
                if (fields.length != 2 || fields[0].trim().isEmpty())
                    throw new IllegalArgumentException("Invalid file format");

                int mark = Integer.parseInt(fields[1].trim());
                if (mark < 0 || mark > 100)
                    throw new IllegalArgumentException("Mark must be 0 to 100");

                String grade = mark >= 90 ? "A+" : mark >= 80 ? "A" :
                        mark >= 70 ? "B" : mark >= 60 ? "C" :
                        mark >= 50 ? "D" : "F";
                return new FileResult(file.getFileName().toString(), true,
                        "Mark=" + mark + ", Grade=" + grade);
            } catch (IOException | IllegalArgumentException e) {
                return new FileResult(file.getFileName().toString(), false,
                        e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    private static List<Path> createSampleFiles(Path folder) throws IOException {
        Files.createDirectories(folder);
        List<Path> files = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            Path file = folder.resolve("student" + i + ".csv");
            files.add(file);
            if (i % 100 == 0) {
                continue; // intentionally missing
            } else if (i % 97 == 0) {
                Files.write(file, "CORRUPT DATA".getBytes("UTF-8"));
            } else {
                String data = "REG" + i + "," + (i % 101);
                Files.write(file, data.getBytes("UTF-8"));
            }
        }
        return files;
    }

    private static void runWithPool(List<Path> files, int poolSize)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        ConcurrentLinkedQueue<FileResult> results =
                new ConcurrentLinkedQueue<>();
        List<Future<FileResult>> futures = new ArrayList<>();
        long start = System.nanoTime();

        try {
            for (Path file : files)
                futures.add(executor.submit(new ProcessFile(file)));

            for (Future<FileResult> future : futures) {
                try {
                    results.add(future.get());
                } catch (ExecutionException e) {
                    results.add(new FileResult("unknown-task", false,
                            "Unexpected: " + e.getCause()));
                }
            }
        } finally {
            executor.shutdown();
        }

        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        int success = 0, failure = 0;
        for (FileResult result : results) {
            if (result.success) success++;
            else failure++;
        }
        System.out.println("Pool size: " + poolSize);
        System.out.println("Successful files: " + success);
        System.out.println("Failed files: " + failure);
        System.out.println("Total processed: " + (success + failure));
        System.out.println("Time: " + elapsedMs + " ms");
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) throws Exception {
        Path folder = Paths.get("result-input");
        List<Path> files = createSampleFiles(folder);
        System.out.println("Expected input files: " + files.size());
        runWithPool(files, 1);
        runWithPool(files, 2);
        runWithPool(files, 4);
        runWithPool(files, 8);
    }
}