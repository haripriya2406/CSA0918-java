import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlaylistBenchmark {
    private static volatile int sink;
    private static long time(String label, Runnable action) {
        long start = System.nanoTime(); action.run();
        long elapsed = System.nanoTime() - start;
        System.out.println(label + "," + elapsed / 1_000_000.0);
        return elapsed;
    }
    private static List<Integer> arrayList(int n) {
        List<Integer> x = new ArrayList<>(); for (int i=0;i<n;i++) x.add(i); return x;
    }
    private static List<Integer> linkedList(int n) {
        List<Integer> x = new LinkedList<>(); for (int i=0;i<n;i++) x.add(i); return x;
    }
    public static void main(String[] args) {
        System.out.println("operation,size,elapsed_ms");
        int[] sizes = {1000, 10000, 50000};
        for (int n : sizes) {
            final List<Integer> a = arrayList(n);
            final List<Integer> l = linkedList(n);
            time("ArrayList_add_end_"+n, () -> a.add(n));
            time("LinkedList_add_end_"+n, () -> l.add(n));
            time("ArrayList_front_insert_"+n, () -> a.add(0, -1));
            time("LinkedList_front_insert_"+n, () -> l.add(0, -1));
            time("ArrayList_middle_remove_"+n, () -> a.remove(a.size()/2));
            time("LinkedList_middle_remove_"+n, () -> l.remove(l.size()/2));
            time("ArrayList_get_middle_"+n, () -> sink += a.get(a.size()/2));
            time("LinkedList_get_middle_"+n, () -> sink += l.get(l.size()/2));
            time("ArrayList_iterate_"+n, () -> { for (Integer v:a) sink += v; });
            time("LinkedList_iterate_"+n, () -> { for (Integer v:l) sink += v; });
            final ArrayDeque<Integer> d = new ArrayDeque<>();
            time("ArrayDeque_add_remove_ends_"+n, () -> {
                for(int i=0;i<n;i++) d.addLast(i);
                for(int i=0;i<n/2;i++) { d.addFirst(-i); d.removeLast(); }
            });
        }
        System.out.println("sink="+sink);
    }
}
