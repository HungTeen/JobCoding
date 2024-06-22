package love.pangteen.problems.alibaba.internship_240506;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 19:02
 **/
public class XorSequence {

    private static final int MOD = 1000000007;

    /**
     * DP。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = 32;
        long[][] dp = new long[n + 1][m];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < m; j++) {
                long tmp = qpow(2, a[i - 1] - 1);
                dp[i][j] = (dp[i - 1][j] * tmp % MOD + dp[i - 1][j ^ i] * tmp % MOD) % MOD;
            }
        }
        System.out.println(dp[n][0] - 1);
    }

    private static long qpow(long x, int p){
        long res = 1;
        while(p > 0) {
            if((p & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            p >>= 1;
        }
        return res;
    }
}
