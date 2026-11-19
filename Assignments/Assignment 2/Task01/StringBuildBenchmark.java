import java.util.Locale;

public class StringBuildBenchmark {
    private static volatile int sink;

    private interface BuilderWork { String build(int tickets, int fields); }

    private static String concat(int tickets, int fields) {
        String last = "";
        for (int t = 0; t < tickets; t++) {
            String ticket = "";
            for (int f = 0; f < fields; f++) ticket = ticket + "|FIELD" + f;
            last = ticket;
        }
        return last;
    }

    private static String stringBuilder(int tickets, int fields) {
        String last = "";
        for (int t = 0; t < tickets; t++) {
            StringBuilder b = new StringBuilder();
            for (int f = 0; f < fields; f++) b.append("|FIELD").append(f);
            last = b.toString();
        }
        return last;
    }

    private static String stringBuffer(int tickets, int fields) {
        String last = "";
        for (int t = 0; t < tickets; t++) {
            StringBuffer b = new StringBuffer();
            for (int f = 0; f < fields; f++) b.append("|FIELD").append(f);
            last = b.toString();
        }
        return last;
    }

    private static void measure(String name, BuilderWork work, int tickets, int fields) {
        long total = 0;
        int repeats = 5;
        for (int r = 0; r < repeats; r++) {
            long start = System.nanoTime();
            String result = work.build(tickets, fields);
            total += System.nanoTime() - start;
            sink += result.length();
        }
        System.out.printf(Locale.US, "%s,%d,%d,%.3f%n", name, tickets, fields,
                total / (repeats * 1_000_000.0));
    }

    public static void main(String[] args) {
        System.out.println("method,tickets,fields,average_ms_over_5_runs");
        int[][] workloads = { { 1, 6 }, { 10000, 50 }, { 1000, 200 } };
        for (int[] w : workloads) {
            measure("StringPlus", StringBuildBenchmark::concat, w[0], w[1]);
            measure("StringBuilder", StringBuildBenchmark::stringBuilder, w[0], w[1]);
            measure("StringBuffer", StringBuildBenchmark::stringBuffer, w[0], w[1]);
        }
        System.out.println("Ignore sink value: " + sink);
    }
}
