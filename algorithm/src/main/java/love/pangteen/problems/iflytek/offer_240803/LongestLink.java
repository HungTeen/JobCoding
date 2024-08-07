package love.pangteen.problems.iflytek.offer_240803;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/7 23:13
 **/
public class LongestLink {

    private static int ans = 1;

    /**
     * 树上DP。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        Arrays.setAll(f, i -> in.nextInt() - 1);
        List<Integer>[] edges = new List[n];
        Arrays.setAll(edges, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            edges[x].add(y);
            edges[y].add(x);
        }
        dfs(edges, f, 0, -1, false);
        dfs(edges, f, 0, -1, true);
        System.out.println(ans);
    }

    private static int dfs(List<Integer>[] edges, int[] f, int x, int fa, boolean inc) {
        int max = 0, maxx = 0;
        for (int y : edges[x]) {
            if (y != fa) {
                int val = dfs(edges, f, y, x, inc);
                boolean valid = false;
                if (inc && f[y] >= f[x]) {
                    valid = true;
                } else if (!inc && f[y] <= f[x]) {
                    valid = true;
                }
                if(valid){
                    if(max < val){
                        max = val;
                    } else if(maxx < val){
                        maxx = val;
                    }
                }
            }
        }
        ans = Math.max(ans, max + maxx + 1);
        return max + 1;
    }

    /*
    5
1 2 3 4 3
1 2
2 3
1 4
4 5
     */
}
