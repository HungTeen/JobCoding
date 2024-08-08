package love.pangteen.problems.mihoyo.offer_240803;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/8 21:15
 **/
public class ComplexSwapNearby {

    /**
     * 维护初始TreeMap，增量更新。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] f = new long[n];
        Arrays.setAll(f, i -> in.nextInt());
        TreeMap<Long, Integer> bins = new TreeMap<>((a, b) -> Long.compare(b, a));
        for(int i = 1; i < n; ++ i){
            bins.merge(f[i] * f[i - 1], 1, Integer::sum);
        }
        long ans = 0;
        for(int i = 1; i < n; ++i){
            int x = i - 1, y = i;
            if(y + 1 < n) {
                update(bins, f[y] * f[y + 1], -1);
                update(bins, f[x] * f[y + 1], 1);
            }
            if(x - 1 >= 0){
                update(bins, f[x] * f[x - 1], -1);
                update(bins, f[y] * f[x - 1], 1);
            }
            ans = Math.max(ans, bins.firstKey());
            if(y + 1 < n) {
                update(bins, f[y] * f[y + 1], 1);
                update(bins, f[x] * f[y + 1], -1);
            }
            if(x - 1 >= 0){
                update(bins, f[x] * f[x - 1], 1);
                update(bins, f[y] * f[x - 1], -1);
            }
        }
        System.out.println(ans);
    }

    private static void update(TreeMap<Long, Integer> bins, long key, int delta){
        bins.compute(key, (k ,v) -> {
            if(v == null) return delta;
            return v + delta == 0 ? null : v + delta;
        });
    }

}
