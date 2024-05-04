package main.java.problems.shopee.internship_0429;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 15:38
 **/
public class ReverseWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = s.length();
        int first = -1;
        for(int i = 0; i < n; ++ i){
            if(s.charAt(i) == ' ') {
                System.out.print(" ");
                first = -1;
            } else {
                if(first == -1) first = i;
                if(i == n - 1 || s.charAt(i + 1) == ' '){
                    for(int j = i; j >= first; -- j){
                        System.out.print(s.charAt(j));
                    }
                }
            }
        }
    }
}
