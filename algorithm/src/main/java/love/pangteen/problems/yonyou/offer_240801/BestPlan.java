package love.pangteen.problems.yonyou.offer_240801;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 20:32
 **/
public class BestPlan {

    /**
     * DP。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        for(int i = 0; i < n; ++ i){
            f[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] dp = new int[n];
        int[] mark = new int[n];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;
        mark[0] = -1;
        for(int i = 1; i < n; ++ i){
            for(int j = Math.max(0, i - m); j < i; ++ j){
                if(f[j] == -1) continue;
                int now = dp[j] + f[i - 1];
                if(dp[i] > now){
                    dp[i] = now;
                    mark[i] = j;
                }
            }
        }
        if(dp[n - 1] == 1e9){
            System.out.println(-1);
        } else {
            int now = n - 1;
            List<Integer> ans = new ArrayList<>();
            while(now != -1){
                ans.add(now + 1);
                now = mark[now];
            }
            for(int i = ans.size() - 1; i >= 0; -- i){
                System.out.print(ans.get(i) + (i == 0 ? "\n" : " "));
            }
        }
    }
}
