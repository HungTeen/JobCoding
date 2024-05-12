package main.java.problems.kuaishou.internship_240508;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/9 22:24
 **/
public class Overlap {

    /**
     * 扫描线 + 线段树，x方向和y方向各扫描一次。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] rects = new int[n][4];
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < 4; ++ j){
                rects[i][j] = scanner.nextInt();
            }
        }
        int ans = 0;
        List<int[]> sides = new ArrayList<>();
        for(int i = 0; i < n; ++ i){
            sides.add(new int[]{rects[i][0], rects[i][2], 1, rects[i][1]});
            sides.add(new int[]{rects[i][0], rects[i][2], -1, rects[i][3]});
        }
        ans += cal(sides);
        sides.clear();
        for(int i = 0; i < n; ++ i){
            sides.add(new int[]{rects[i][1], rects[i][3], 1, rects[i][0]});
            sides.add(new int[]{rects[i][1], rects[i][3], -1, rects[i][2]});
        }
        ans += cal(sides);
        System.out.println(ans);
    }

    private static int cal(List<int[]> sides){
        sides.sort(Comparator.comparingInt((int[] a) -> a[3]).thenComparingInt(a -> -a[2]));
        Map<Integer, Boolean> map = new HashMap<>();
        Map<Integer, Integer> id = new HashMap<>();
        for(int i = 0; i < sides.size(); ++ i){
            map.put(sides.get(i)[0], true);
            map.put(sides.get(i)[1], true);
        }
        ids = map.keySet().stream().sorted().toList();
        int n = ids.size();
        for(int i = 0; i < n; ++ i){
            id.put(ids.get(i), i);
//            System.out.println(ids.get(i) + " " + i);
        }
        int[] cnt = new int[n << 2];
        int[] len = new int[n << 2];
        int ans = 0;
        int lastRes = 0;
        for(int i = 0; i < sides.size(); ++ i){
            int l = id.get(sides.get(i)[0]);
            int r = id.get(sides.get(i)[1]);
            int type = sides.get(i)[2];
            addEdge(cnt, len, 1, 0, n - 1, l, r - 1, type);
//            System.out.println(len[1]);
            ans += Math.abs(len[1] - lastRes);
            lastRes = len[1];
        }
        return ans;
    }

    private static List<Integer> ids;

    private static void addEdge(int[] cnt, int[] len, int k, int l, int r, int x, int y, int d){
        if(x <= l && r <= y){
            cnt[k] += d;
            update(cnt, len, k, l, r);
            return;
        }
        int mid = l + r >> 1;
        if(mid >= x) addEdge(cnt, len, k << 1, l, mid, x, y, d);
        if(mid < y) addEdge(cnt, len, k << 1 | 1, mid + 1, r, x, y, d);
        update(cnt, len, k, l, r);
    }

    private static void update(int[] cnt, int[] len, int k, int l, int r){
        if(cnt[k] > 0){
            len[k] = ids.get(r + 1) - ids.get(l);
        } else if(l == r){
            len[k] = 0;
        } else {
            len[k] = len[k << 1] + len[k << 1 | 1];
        }
    }

}
