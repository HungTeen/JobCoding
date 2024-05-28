package main.java.problems.ant.internship_240511;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/28 13:24
 **/
public class ArrayWeight {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * 推数学公式，有点难度。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        long m = scanner.nextInt();
        long ans = qpow(m , n);
        ans = (ans + (n - 1) * (m - 1) % MOD * qpow(m, n - 1) % MOD) % MOD;
        System.out.println(ans);
    }

    private static long qpow(long x, long p){
        long res = 1;
        while(p > 0){
            if((p & 1) == 1){
                res = res * x % MOD;
            }
            x = x * x % MOD;
            p >>= 1;
        }
        return res;
    }
}
