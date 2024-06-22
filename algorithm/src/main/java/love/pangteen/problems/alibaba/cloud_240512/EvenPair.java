package love.pangteen.problems.alibaba.cloud_240512;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/12 23:06
 **/
public class EvenPair {

    private static long ans = 0;

    /**
     * 遍历找不相邻偶数累计答案。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer>[] edges = new List[n + 1];
        for(int i = 0; i <= n; ++ i) edges[i] = new ArrayList<>();
        for(int i = 1; i < n; ++ i){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges[u].add(v);
            edges[v].add(u);
        }
        dfs(edges, 1, 0, n);
        System.out.println(ans >> 1);
    }

    private static void dfs(List<Integer>[] edges, int now, int fa, int n){
        int closeEven = (now % 2 == 0 ? 1 : 0);
        for(int nxt : edges[now]){
            if(nxt % 2 == 0) closeEven ++;
        }
        if(now % 2 == 0){
            ans += n - 1 - edges[now].size();
        } else {
            ans += n / 2 - closeEven;
        }
        for(int nxt : edges[now]){
            if(nxt != fa){
                dfs(edges, nxt, now, n);
            }
        }
    }
}
