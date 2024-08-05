package love.pangteen.problems.oppo.offer_240727;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 16:21
 **/
public class TwoSequence2 {

    private static final int MOD = (int) (1e9 + 7);
    /**
     * 二分 + 排序。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        int[] g = new int[n];
        for (int i = 0; i < n; ++i) f[i] = in.nextInt();
        for (int i = 0; i < n; ++i) g[i] = in.nextInt();
        Arrays.sort(f);
        Arrays.sort(g);
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = 0, r = n - 1;
            int res = -1;
            while (l <= r) {
                int mid = l + r >> 1;
                if (g[mid] >= f[i]) {
                    res = mid;
                    r = mid - 1;
                } else l = mid + 1;
            }
            ans = (ans + ((qpow(2, i + 1) - 1 + MOD) % MOD * (qpow(2, n - res) - 1 + MOD) % MOD)) % MOD;
        }
        System.out.println(ans);
    }

    private static long qpow(long n, long p){
        long res = 1;
        while(p > 0){
            if((p & 1) == 0) res = res * n % MOD;
            p >>= 1;
            n = n * n % MOD;
        }
        return res;
    }
}
