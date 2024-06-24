package love.pangteen.problems.huawei.internship_240522;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/23 11:35
 **/
public class BestIndexChoice {

    private static List<int[]>[] groups;
    private static List<Integer>[] edges;
    private static int ans = 0;

    /**
     * 树上背包问题。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        groups = new List[m];
        edges = new List[m];
        for(int i = 0; i < m; ++ i){
            groups[i] = new ArrayList<>();
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; ++ i){
            int g = scanner.nextInt();
            int v = scanner.nextInt();
            int c = scanner.nextInt();
            groups[g].add(new int[]{v, c});
        }
        int root = -1;
        for(int i = 0; i < m; ++ i){
            int x = scanner.nextInt();
            if(x == -1) root = i;
            else edges[x].add(i);
        }
        dfs(root, b, new int[b + 1]);
        System.out.println(ans);
    }

    private static void dfs(int now, int b, int[] old){
        int[] dp = Arrays.copyOf(old, b + 1);
        int[] vis = new int[b + 1];
        for(int i = 0; i < groups[now].size(); ++ i){
            int v = groups[now].get(i)[0];
            int c = groups[now].get(i)[1];
            for(int j = b; j >= c; -- j){
                if(dp[j - c] >= 0) {
                    int val = dp[j - c] + v;
                    dp[j] = Math.max(dp[j], val);
                    vis[j] = Math.max(vis[j], val);
                }
            }
        }
        for(int i = 0; i <= b; ++ i) {
            if(vis[i] == 0) dp[i] = -1;
            else dp[i] = vis[i];
            ans = Math.max(ans, dp[i]);
        }
        for(int i = 0; i < edges[now].size(); ++ i){
            dfs(edges[now].get(i), b, dp);
        }
    }
}
