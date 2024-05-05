package main.java.problems.alibaba.internship_240421;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 18:46
 **/
public class VowelPair {

    private static final int MOD = 1000000007;

    /**
     * 计算每个位置对的贡献，快速幂。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long ans = ((1L * n * (n - 1)) >> 1) - (n - 1);
        ans = ((ans * 25) % MOD) * qpow(26, n - 2) % MOD;
        System.out.println(ans);
    }

    private static int qpow(int a, int p){
        int res = 1;
        while(p > 0){
            if((p & 1) == 1){
                res = res * a % MOD;
            }
            a = a * a % MOD;
            p >>= 1;
        }
        return res;
    }
}
