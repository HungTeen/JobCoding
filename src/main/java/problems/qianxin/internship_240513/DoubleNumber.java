package main.java.problems.qianxin.internship_240513;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/4 14:51
 **/
public class DoubleNumber {

    /**
     * 只有最长的时候需要特殊处理。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        String num = String.valueOf(n);
        long ans = 0;
        long cnt = 1;
        for(int i = 2; i <= num.length(); i += 2){
            if(i == num.length()){
                long a = Long.parseLong(num.substring(0, i / 2));
                long b = Long.parseLong(num.substring(i / 2));
                ans += Math.min(a, b);
            } else {
                ans += cnt * 9;
            }
            cnt *= 10;
        }
        System.out.println(ans);
    }
}
