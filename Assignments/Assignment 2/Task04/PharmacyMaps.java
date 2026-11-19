import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PharmacyMaps {
    private static void add(Map<String,Integer> map, int n) {
        for(int i=0;i<n;i++) map.put(String.format("MED%06d", (n-i)*3), i+10);
    }
    private static long lookups(Map<String,Integer> map, int n) {
        long start=System.nanoTime();
        for(int i=0;i<100000;i++) map.get(String.format("MED%06d", ((i%n)+1)*3));
        return System.nanoTime()-start;
    }
    public static void main(String[] args) {
        System.out.println("map,size,put_ms,100000_get_ms,iterate_ms");
        int[] sizes={1000,10000,50000};
        for(int n:sizes) {
            Map<String,Integer> h=new HashMap<>(), t=new TreeMap<>(), l=new LinkedHashMap<>();
            long s=System.nanoTime(); add(h,n); long hp=System.nanoTime()-s;
            s=System.nanoTime(); add(t,n); long tp=System.nanoTime()-s;
            s=System.nanoTime(); add(l,n); long lp=System.nanoTime()-s;
            Map<String,Integer>[] maps=new Map[]{h,t,l}; String[] names={"HashMap","TreeMap","LinkedHashMap"};
            for(int i=0;i<maps.length;i++) {
                long get=lookups(maps[i],n); s=System.nanoTime();
                for(Map.Entry<String,Integer> e:maps[i].entrySet()) { if(e.getValue()==-1) System.out.print(""); }
                long iter=System.nanoTime()-s;
                long put=i==0?hp:(i==1?tp:lp);
                System.out.println(names[i]+","+n+","+put/1e6+","+get/1e6+","+iter/1e6);
            }
            System.out.println("TreeMap code-order first="+t.keySet().iterator().next());
            System.out.println("LinkedHashMap insertion-order first="+l.keySet().iterator().next());
        }
    }
}
