package main.java.problems.meituan.internship_0427;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 20:55
 **/
public class SpecialRectangle {

    /**
     * 暴力枚举。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = scanner.next().toCharArray();
        }
        int ans = 0;
        for(int i = 0; i < n - 1; ++ i){
            for(int j = 0; j < m - 1; ++ j){
                Set<Character> set = new HashSet<>();
                set.add(matrix[i][j]);
                set.add(matrix[i + 1][j]);
                set.add(matrix[i][j + 1]);
                set.add(matrix[i + 1][j + 1]);
                if(set.size() == 4){
                    ++ ans;
                }
            }
        }
        System.out.println(ans);
    }

}
