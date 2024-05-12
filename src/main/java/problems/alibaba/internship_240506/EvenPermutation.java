package main.java.problems.alibaba.internship_240506;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 18:31
 **/
public class EvenPermutation {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int even = n / 2, odd = n - even;
        int least = Math.min(n - 1, even); // 最少有这么多对。
        if(k < least || k >= n){
            System.out.println(-1);
            return;
        }
        int[] ans = new int[n];
        int insert = Math.max(0, k - least); // 在连续的偶数中需要间隔插入几个奇数。
        int m = Math.max(0, even - insert);
        for(int i = 0; i < m; ++ i){
            ans[i] = even << 1;
            -- even;
        }
        int pos = m;
        while (even > 0 || odd > 0){
            if(odd > 0){
                -- odd;
                ans[pos ++] = odd << 1 | 1;
            }
            if(even > 0){
                ans[pos ++] = even << 1;
                -- even;
            }
        }
        for(int i = 0; i < n; ++ i){
            System.out.print(ans[i] + (i == n - 1 ? "\n" : " "));
        }
    }
}
