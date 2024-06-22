package love.pangteen.problems.g_bits.internship_240516;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 17:32
 **/
public class WalkOnGrid {

    /**
     * 动态规划。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] f = new int[n][m];
        // dp[i][j][0] 此点的最大行动力。
        // dp[i][j][1] 到达此点的最小行动力。
        int[][][] dp = new int[n][m][2];
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < m; ++ j){
                f[i][j] = scanner.nextInt();
            }
        }
        dp[0][0][0] = dp[0][0][1] = f[0][0];
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < m; ++ j){
                if(i == 0 && j == 0) continue;
                int now = Integer.MIN_VALUE;
                int tmp = Integer.MIN_VALUE;
                if(i > 0){
                    now = Math.max(now, dp[i - 1][j][0]);
                    tmp = Math.max(tmp, dp[i - 1][j][1]);
                }
                if(j > 0){
                    now = Math.max(now, dp[i][j - 1][0]);
                    tmp = Math.max(tmp, dp[i][j - 1][1]);
                }
                dp[i][j][0] = now + f[i][j];
                dp[i][j][1] = Math.min(tmp, dp[i][j][0]);
                System.out.println(dp[i][j][0] + "," + dp[i][j][1]);
            }
        }
        System.out.println(Math.max(0, 1 - dp[n - 1][m - 1][1]));
    }
}
