package main.java.problems.alibaba.internship_240423;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 17:01
 **/
public class RingString {

    /**
     * S + S.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String s = str + str;
        int now = 1;
        int ans = 1;
        for(int i = 1; i < s.length(); ++ i){
            if(s.charAt(i) == s.charAt(i - 1)){
                ++ now;
                ans = Math.max(ans, now);
            } else {
                now = 1;
            }
        }
        System.out.println(Math.min(ans, str.length()));
    }
}
