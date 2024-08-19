package love.pangteen.problems.jingdong.offer_240817;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/17 12:04
 **/
public class PathCount {

    private static boolean[] vis;
    private static List<int[]>[] edges;
    private static final int MOD = 20220201;
    private static boolean soBig = false;
    private static int ans = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), a = in.nextInt();
        edges = new List[n + 1];
        Arrays.setAll(edges, i -> new ArrayList<>());
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
            edges[u].add(new int[]{v, w});
            edges[v].add(new int[]{u, w});
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        dfs(1, n, a, map);
        if (soBig)
            System.out.println("All roads lead to Home!");
        System.out.println(ans);
    }

    private static void dfs(int now, int dst, int limit, Map<Integer, Integer> tmp) {
        if (now == dst) {
            ans += tmp.getOrDefault(limit, 0);
            if (ans >= MOD) {
                ans %= MOD;
                soBig = true;
            }
            return;
        }
        Map<Integer, List<Integer>> cur = new HashMap<>();
        for (int[] nxt : edges[now]) {
            cur.computeIfAbsent(nxt[0], k -> new ArrayList<>()).add(nxt[1]);
        }
        for (Map.Entry<Integer, List<Integer>> ee : cur.entrySet()) {
            Map<Integer, Integer> bin = new HashMap<>();
            for (int w : ee.getValue()) {
                for (Map.Entry<Integer, Integer> e : tmp.entrySet()) {
                    if (w + e.getKey() <= limit) {
                        bin.merge(w + e.getKey(), e.getValue(), Integer::sum);
                    }
                }
            }
            if (bin.size() > 0) {
                dfs(ee.getKey(), dst, limit, bin);
            }
        }
    }

}
