import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class RedesignDemo {
    private static volatile int sink;
    private static long testList(int n) {
        List<Integer> queue=new ArrayList<>();
        for(int i=0;i<n;i++) queue.add(i);
        long start=System.nanoTime();
        for(int i=0;i<n/2;i++) { queue.add(0,-i); queue.remove(queue.size()-1); }
        sink+=queue.get(0);
        return System.nanoTime()-start;
    }
    private static long testDeque(int n) {
        ArrayDeque<Integer> queue=new ArrayDeque<>();
        for(int i=0;i<n;i++) queue.addLast(i);
        long start=System.nanoTime();
        for(int i=0;i<n/2;i++) { queue.addFirst(-i); queue.removeLast(); }
        sink+=queue.peekFirst();
        return System.nanoTime()-start;
    }
    public static void main(String[] args) {
        System.out.println("structure,size,front_add_and_opposite_end_remove_ms");
        for(int n:new int[]{1000,10000,50000}) {
            System.out.println("ArrayList,"+n+","+testList(n)/1e6);
            System.out.println("ArrayDeque,"+n+","+testDeque(n)/1e6);
        }
        System.out.println("sink="+sink);
    }
}
