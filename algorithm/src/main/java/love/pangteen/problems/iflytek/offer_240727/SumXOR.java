package love.pangteen.problems.iflytek.offer_240727;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/30 14:29
 **/
public class SumXOR {

    private static final int MOD = (int)(1e9 + 7);

    /**
     * 有点难：二维DP + 前缀优化。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] dp = new int[2 * m + 1][m + 1];
        int[][] tmp = new int[2 * m + 1][m + 1];
        for(int i = 0; i <= m; ++ i) tmp[0][i] = 1;
        for(int i = 1; i <= n; ++ i){
            for(int j = 0; j <= 2 * m; ++ j){
                for(int v = 0; v <= m; ++ v){
                    int xor = (j ^ v);
                    if(xor <= 2 * m)
                    dp[j][v] = (dp[j][v] + tmp[xor][v]) % MOD;
                }
            }
            // 前缀和。
            for(int j = 0; j <= 2 * m; ++ j){
                for(int k = 1; k <= m; ++ k){
                    dp[j][k] = (dp[j][k] + dp[j][k - 1]) % MOD;
                }
            }
            // 滚动
            for(int j = 0; j <= 2 * m; ++ j){
                for(int k = 0; k <= m; ++ k){
                    tmp[j][k] = dp[j][k];
                    dp[j][k] = 0;
                }
            }
        }
        System.out.println(tmp[m][m]);
    }
}
