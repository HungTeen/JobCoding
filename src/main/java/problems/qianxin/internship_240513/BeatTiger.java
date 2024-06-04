package main.java.problems.qianxin.internship_240513;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/4 15:03
 **/
public class BeatTiger {

    /**
     * 01背包。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hp = scanner.nextInt();
        int att = scanner.nextInt();
        int n = scanner.nextInt();
        int[] h = new int[n];
        int[] a = new int[n];
        int[] m = new int[n];
        int[] dp = new int[hp + 1];
        for (int i = 0; i < n; ++i) h[i] = scanner.nextInt();
        for (int i = 0; i < n; ++i) a[i] = scanner.nextInt();
        for (int i = 0; i < n; ++i) m[i] = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            a[i] = Math.max(0, (h[i] + att - 1) / att - 1) * a[i]; // 杀死需要损失的血量。
        }
        for (int i = 0; i < n; ++i) {
            for (int j = hp; j >= a[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - a[i]] + m[i]);
            }
        }
        System.out.println(dp[hp - 1]);
    }

    /*
    样例1：
1 1
4
1 1 2 1
1 1 1 1
1 2 4 8
     */

    /*
    样例2：
6 3
3
4 5 6
5 4 3
1 2 1
     */

    /*
    样例3：

     */
}
