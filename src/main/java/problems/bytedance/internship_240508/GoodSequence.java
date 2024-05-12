package main.java.problems.bytedance.internship_240508;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 17:31
 **/
public class GoodSequence {

    private static final int MOD = 1000000007;

    /**
     * 公式。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();
        int cntA = 0, cntB = 0;
        for(int i = 0; i < n; ++ i){
            if(s.charAt(i) == 'b'){
                cntB ++;
            }
        }
        long ans = 0;
        for(int i = 0; i < n; ++ i){
            if(s.charAt(i) == 'a') {
                long res = ((qpow(2, i - cntA) * ((qpow(2, cntB) - 1 + MOD) % MOD)) % MOD) * qpow(2, n - i - 1 - cntB) % MOD;
                ans = (ans + res) % MOD;
                ++ cntA;
            } else if(s.charAt(i) == 'b') {
                --cntB;
            }
        }
        System.out.println(ans);
    }

    private static long qpow(long a, int p){
        long res = 1;
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
