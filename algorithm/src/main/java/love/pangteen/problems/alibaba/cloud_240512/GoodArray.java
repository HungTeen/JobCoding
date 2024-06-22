package love.pangteen.problems.alibaba.cloud_240512;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/12 22:33
 **/
public class GoodArray {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * Shit problem.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] cnt = new int[2];
        for(int i = 0; i < n; ++ i){
            int x = scanner.nextInt();
            cnt[x % 2] ++;
        }
        long ans = 0;
        long res = 1;
        for(int i = 1; i <= Math.min(cnt[0], cnt[1]); ++ i){
            res = res * ((cnt[0] - i + 1) * inv(i) % MOD) % MOD * ((cnt[1] - i + 1) * inv(i) % MOD) % MOD;
            System.out.println(res);
            ans = (ans + res) % MOD;
        }
        System.out.println(ans);
    }

    private static long inv(long x){
        return pow(x, MOD - 2);
    }

    private static long pow(long x, long p){
        long res = 1;
        while(p > 0){
            if((p & 1) == 1) res = (res * x) % MOD;
            x = x * x % MOD;
            p >>= 1;
        }
        return res;
    }
}
