package main.java.problems.alibaba.cloud_240421;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 19:17
 **/
public class SameForest {

    /**
     * 并查集 + 前缀和。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] dad = new int[n];
        int[] cnt = new int[n];
        for(int i = 0; i < n; ++ i) {
            dad[i] = i;
            cnt[i] = 1;
        }
        while(m -- > 0){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int fa = seek(dad, a);
            int fb = seek(dad, b);
            if(fa != fb){
                dad[fb] = fa;
                cnt[fa] += cnt[fb];
            }
        }
        List<Integer> groups = new ArrayList<>();
        for(int i = 0; i < n; ++ i){
            if(seek(dad, i) == i){
                groups.add(cnt[i]);
            }
        }
        groups.sort(Integer::compare);
        long ans = Long.MAX_VALUE;
        long[] pre = new long[groups.size() + 1];
        for(int i = 1; i <= groups.size(); ++ i){
            pre[i] = pre[i - 1] + groups.get(i - 1);
        }
        for(int i = 0; i < groups.size(); ++ i){
            long k = groups.get(i);
            long res = (k * i - pre[i]) + (pre[groups.size()] - pre[i] - k * (groups.size() - i));
            ans = Math.min(ans, res);
        }
        System.out.println(ans);
    }

    private static int seek(int[] dad, int x) {
        return dad[x] == x ? x : (dad[x] = seek(dad, dad[x]));
    }
}
