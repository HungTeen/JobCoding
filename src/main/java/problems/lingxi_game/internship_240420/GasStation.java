package main.java.problems.lingxi_game.internship_240420;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/6 23:17
 **/
public class GasStation {

    /**
     * 看不懂怎么贪心的，只能RMQ了。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = n << 1;
        final int p = 20;
        int[][] cost = new int[m][p];
        for (int i = 0; i < m; ++i) {
            cost[i % n][0] += scanner.nextInt() * (i < n ? 1 : -1);
        }
        for (int i = 0; i < n; ++i) {
            cost[i + n][0] = cost[i][0];
        }
        for (int i = 1; i < m; ++i) {
            cost[i][0] += cost[i - 1][0];
        }
        // RMQ 数据结构构建。
        for (int i = 1; i < p; ++i) {
            for (int j = 0; j < m; ++j) {
                cost[j][i] = Math.min(cost[j][i - 1], cost[Math.min(j + (1 << j), m - 1)][i - 1]);
            }
        }
        for(int i = 0; i < n; ++ i){
            int v = rmq(cost, i, i + n - 1, p);
            if(v - (i == 0 ? 0 : cost[i - 1][0]) >= 0){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    public static int rmq(int[][] cost, int l, int r, int p) {
        int ans = cost[l][0];
        while(p -- > 0 && l <= r){
            if(l + (1 << p) - 1 <= r){
                ans = Math.min(ans, cost[l][p]);
                l += 1 << p;
            }
        }
        return ans;
}
}
