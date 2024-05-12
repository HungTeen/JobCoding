package main.java.problems.alibaba.internship_240506;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 19:16
 **/
public class STDXorSequence {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            num[i] = scanner.nextInt();
        }
        long res = 0;
        for(int i = 0; i < 1 << n; i ++)
        {
            int ans = 0;
            for(int j = 0; j < n; j ++)
            {
                if(((i >> j) & 1) == 1)
                {
                    ans ^= (j + 1);
                }
            }
            long t = 1;
            if(ans == 0)
            {
                for(int k = 1; k <= n; k ++)
                {
                    t = (t * qpow(2, num[k] - 1)) % MOD;
                }
                res = (res + t) % MOD;
            }

        }
        System.out.println((res - 1 + MOD) % MOD);
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
