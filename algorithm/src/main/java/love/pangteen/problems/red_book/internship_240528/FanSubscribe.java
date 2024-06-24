package love.pangteen.problems.red_book.internship_240528;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/24 12:09
 **/
public class FanSubscribe {

    /**
     * 动态规划,dp[i][j]表示最小需要的账号数：i表示是否已经选择的一个账号进行多次发布，j表示当前达到的关注数。
     */
    public static void main(String[] args) {
        final int max = (int) 1e9;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] dp = new int[2][k + 1];
        int[] f = new int[n + 1];
        Arrays.fill(dp[0], max);
        Arrays.fill(dp[1], max);
        for(int i = 1; i <= n; ++ i) {
            f[i] = scanner.nextInt();
        }
        dp[0][0] = 0;
        for(int i = 1; i <= n; ++ i){
            for(int j = k; j >= f[i]; -- j){
                dp[1][j] = Math.min(dp[1][j], dp[0][j - f[i]] + 1);
            }
            int x = (f[i] >> 1);
            for(int j = k; j >= x; -- j){
                dp[0][j] = Math.min(dp[0][j], dp[0][j - x] + 1);
                dp[1][j] = Math.min(dp[1][j], dp[1][j - x] + 1);
            }
        }
        int ans = Math.min(dp[0][k], dp[1][k]);
        System.out.println(ans == max ? -1 : ans);
    }
}
