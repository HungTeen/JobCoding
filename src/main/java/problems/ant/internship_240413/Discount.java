package main.java.problems.ant.internship_240413;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/7 22:22
 **/
public class Discount {

    /**
     * 贪心。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double k = scanner.nextInt();
        double[] prices = new double[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        String s = scanner.next();
        for(int i = 0; i < n; ++ i){
            if(s.charAt(i) == '1') prices[i] *= 0.95;
        }
        int ans = 0;
        Arrays.sort(prices);
        for(int i = 0; i < n; ++ i){
            if(prices[i] <= k){
                k -= prices[i];
                ans ++;
            } else {
                break;
            }
        }
        System.out.println(ans);
    }
}
