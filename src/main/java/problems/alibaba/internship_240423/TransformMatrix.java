package main.java.problems.alibaba.internship_240423;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 16:56
 **/
public class TransformMatrix {

    /**
     * Easy.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0 ; i < n; ++ i){
            for(int j = 0; j < n; ++ j){
                matrix[i][j] = scanner.nextInt();
            }
        }
        int ans = 0;
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < i; ++ j){
                ans += (matrix[i][j] - matrix[j][i]) << 1;
            }
        }
        System.out.println(ans);
    }
}
