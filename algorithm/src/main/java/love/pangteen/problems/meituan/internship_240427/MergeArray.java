package love.pangteen.problems.meituan.internship_240427;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 20:55
 **/
public class MergeArray {

    /**
     * dp[i][j]表示将前i个数划分区间，并且最后一个区间所有数之和为j的方案数。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        final int mod = (int) (1e9 + 7);
        int[] f = new int[n];
        for(int i = 0; i < n; ++ i) f[i] = scanner.nextInt();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for(int i = 0; i < n; ++ i){
            dp[i + 1][Math.min(f[i], m)] = dp[i][m]; // 前面i个数剩余的也满足要求，单独开一个区间。
            for(int j = 0; j <= m; ++ j){
                int target = Math.min(j + f[i], m);
                dp[i + 1][target] = (dp[i + 1][target] + dp[i][j]) % mod; // 不管前面区间是否满足，都加入前一个区间。
            }
//            for(int j = 0; j <= m; ++ j){
//                System.out.print(dp[i + 1][j] + " ");
//            }
//            System.out.println();
        }
        int ans = (dp[n][0] + dp[n][m]) % mod;
        System.out.println(ans);
    }

}
