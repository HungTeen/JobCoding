package love.pangteen.problems.net_ease.offer_240817;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/21 23:31
 **/
public class ProtectBase {

    private static int[] sum, lazy;

    /**
     * 线段树 + 离散化。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        List<int[]> lines = new ArrayList<>();
        List<int[]> points = new ArrayList<>();
        List<Integer> scatter = new ArrayList<>();
        for(int i = 0; i < n; ++ i){
            int x = in.nextInt(), y = in.nextInt(), w = in.nextInt();
            lines.add(new int[]{x, y, w});
            scatter.add(x);
            scatter.add(x + w);
        }
        for(int i = 0; i < m; ++ i){
            int x = in.nextInt(), y = in.nextInt();
            scatter.add(x);
            points.add(new int[]{x, y});
        }
        lines.sort(Comparator.comparingInt(a -> a[1]));
        points.sort(Comparator.comparingInt(a -> a[1]));
        List<Integer> list = scatter.stream().sorted(Comparator.naturalOrder()).distinct().toList();
        Map<Integer, Integer> byId = new HashMap<>();
        for(int i = 0; i < list.size(); ++ i){
            byId.put(list.get(i), i);
        }
        int N = list.size();
        sum = new int[N << 2];
        lazy = new int[N << 2];
        int lPos = 0;
        long ans = 0;
        for(var p : points){
            if(p[0] > k) continue;
            while(lPos < lines.size() && lines.get(lPos)[1] <= p[1]){
                int[] line = lines.get(lPos);
                int x = byId.get(line[0]), y = byId.get(line[0] + line[2]);
                update(1, 1, N, x, y, 1);
                ++ lPos;
            }
            int xPos = byId.get(p[0]);
            int res = query(1, 1, N, xPos);
            ans += res;
        }
        System.out.println(ans);
    }

    private static void update(int k, int l, int r, int x, int y, int add){
        if(x <= l && r <= y){
            lazy[k] += add;
            sum[k] += (r - l + 1) * add;
            return;
        }
        int mid = l + r >> 1;
        pushDown(k, l, r, mid);
        if(mid >= x) update(k << 1, l, mid, x, y, add);
        if(mid < y) update(k << 1 | 1, mid + 1, r, x, y, add);
        sum[k] = sum[k << 1] + sum[k << 1 | 1];
    }

    private static int query(int k, int l, int r, int x){
        if(l == r) return sum[k];
        int mid = l + r >> 1;
        pushDown(k, l, r, mid);
        if(mid >= x) return query(k << 1, l, mid, x);
        else return query(k << 1 | 1, mid + 1, r, x);
    }

    private static void pushDown(int k, int l, int r, int mid){
        if(lazy[k] > 0){
            lazy[k << 1] = lazy[k << 1 | 1] = lazy[k];
            sum[k << 1] += (mid - l + 1) * lazy[k];
            sum[k << 1 | 1] += (r - mid) * lazy[k];
            lazy[k] = 0;
        }
    }
}
