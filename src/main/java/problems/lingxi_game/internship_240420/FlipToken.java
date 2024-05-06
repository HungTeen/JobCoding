package main.java.problems.lingxi_game.internship_240420;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/6 23:06
 **/
public class FlipToken {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        scanner.nextLine();
        while(k-- > 0){
            String str = scanner.nextLine();
            int n = str.length();
            char[] s = str.toCharArray();
            int l = -1;
            for(int i = 0; i < n; ++ i){
                if(i == n - 1 || s[i + 1] == ' '){
                    if(l != -1){
                        int len = (i - l + 1);
                        for(int j = 0; j < len / 2; ++ j){
                            char t = s[l + j];
                            s[l + j] = s[i - j];
                            s[i - j] = t;
                        }
                    }
                }
                if(s[i] != ' '){
                    if(l == -1){
                        l = i;
                    }
                } else {
                    l = -1;
                }
            }
            for(int i = n - 1; i >= 0; -- i){
                System.out.print(s[i] + (i == 0 ? "\n" : ""));
            }
        }
    }
}
