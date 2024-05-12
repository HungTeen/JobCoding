package main.java.problems.kuaishou.internship_240508;

import org.w3c.dom.Node;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/9 22:24
 **/
public class BestOverlap {

    /**
     * 扫描线 + 线段树，只扫描一遍。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] rects = new int[n][4];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 4; ++j) {
                rects[i][j] = scanner.nextInt();
            }
        }

        // 扫描线。
        List<int[]> sides = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            sides.add(new int[]{rects[i][0], rects[i][2], 1, rects[i][1]});
            sides.add(new int[]{rects[i][0], rects[i][2], -1, rects[i][3]});
        }
        sides.sort(Comparator.comparingInt((int[] a) -> a[3]).thenComparingInt(a -> -a[2]));

        // 离散化。
        Map<Integer, Boolean> map = new HashMap<>();
        Map<Integer, Integer> id = new HashMap<>();
        for (int i = 0; i < sides.size(); ++i) {
            map.put(sides.get(i)[0], true);
            map.put(sides.get(i)[1], true);
        }
        ids = map.keySet().stream().sorted().toList();
        int m = ids.size();
        for (int i = 0; i < m; ++i) {
            id.put(ids.get(i), i);
        }

        Node[] nodes = new Node[m << 2];
        int ans = 0;
        int lastRes = 0;
        init(nodes, 1, 0, m - 1);
        for (int i = 0; i < sides.size(); ++i) {
            int l = id.get(sides.get(i)[0]);
            int r = id.get(sides.get(i)[1]);
            int type = sides.get(i)[2];
            addEdge(nodes, 1, 0, m - 1, l, r - 1, type);
            ans += Math.abs(nodes[1].len - lastRes);
//            System.out.println(ans);
            if(i < sides.size() - 1){
                ans += (nodes[1].num << 1) * (sides.get(i + 1)[3] - sides.get(i)[3]);
            }
//            System.out.println(ans);
            lastRes = nodes[1].len;
        }
        System.out.println(ans);
    }

    private static List<Integer> ids;

    private static void init(Node[] nodes, int k, int l, int r){
        nodes[k] = new Node();
        if(l == r){
            return;
        }
        int mid = l + r >> 1;
        init(nodes, k << 1, l, mid);
        init(nodes, k << 1 | 1, mid + 1, r);
    }

    private static void addEdge(Node[] nodes, int k, int l, int r, int x, int y, int d) {
        if (x <= l && r <= y) {
            nodes[k].cnt += d;
            update(nodes, k, l, r);
            return;
        }
        int mid = l + r >> 1;
        if (mid >= x) addEdge(nodes, k << 1, l, mid, x, y, d);
        if (mid < y) addEdge(nodes, k << 1 | 1, mid + 1, r, x, y, d);
        update(nodes, k, l, r);
    }

    private static void update(Node[] nodes, int k, int l, int r) {
        if (nodes[k].cnt > 0) {
            nodes[k].len = ids.get(r + 1) - ids.get(l);
            nodes[k].hasL = nodes[k].hasR = true;
            nodes[k].num = 1;
        } else if (l == r) {
            nodes[k].len = 0;
            nodes[k].hasL = nodes[k].hasR = false;
            nodes[k].num = 0;
        } else {
            nodes[k].len = nodes[k << 1].len + nodes[k << 1 | 1].len;
            nodes[k].hasL = nodes[k << 1].hasL;
            nodes[k].hasR = nodes[k << 1 | 1].hasR;
            nodes[k].num = nodes[k << 1].num + nodes[k << 1 | 1].num - (nodes[k << 1].hasR && nodes[k << 1 | 1].hasL ? 1 : 0);
        }
    }

    static class Node {
        public int cnt;  // 区间被覆盖了几次。
        public int len;  // 区间被覆盖的长度。
        public boolean hasL;  // 区间的左端点是否被覆盖。
        public boolean hasR;  // 区间的右端点是否被覆盖。
        public int num;  // 区间中有多少不连续的线段覆盖。
    }

}
