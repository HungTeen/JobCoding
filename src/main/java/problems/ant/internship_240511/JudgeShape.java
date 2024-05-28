package main.java.problems.ant.internship_240511;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/28 12:29
 **/
public class JudgeShape {

    /**
     * 讨厌这种题！
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] points = new int[4][2];
        int q = scanner.nextInt();
        while(q -- > 0){
            for(int i = 0; i < 4; ++ i){
                points[i][0] = scanner.nextInt();
                points[i][1] = scanner.nextInt();
            }
            run(points);
        }
    }

    private static void run(int[][] points){
        for(int i = 0; i < 4; ++ i){
            for(int j = 0; j < 4; ++ j){
                if(i == j) continue;
                for(int k = 0; k < 4; ++ k){
                    if(k == i || k == j) continue;
                    for(int l = 0; l < 4; ++ l){
                        if(l == i || l == j || l == k) continue;
                        if(check(points[i], points[j], points[k], points[l])){
                            System.out.println("YES");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("NO");
    }

    private static boolean check(int[] a, int[] b, int[] c, int[] d){
        boolean p1 = checkParallel(new int[]{a[0] - b[0], a[1] - b[1]}, new int[]{c[0] - d[0], c[1] - d[1]});
        boolean p2 = checkParallel(new int[]{a[0] - d[0], a[1] - d[1]}, new int[]{b[0] - c[0], b[1] - c[1]});
        // p1 平行， p2不平行。
        if(! p1 || p2) return false;
        return (a[0] - d[0]) * (a[0] - d[0]) + (a[1] - d[1]) * (a[1] - d[1]) == (c[0] - b[0]) * (c[0] - b[0]) + (c[1] - b[1]) * (c[1] - b[1]);
    }

    private static boolean checkParallel(int[] s1, int[] s2){
        if(s1[0] == 0 || s2[0] == 0) return s1[0] == s2[0];
        return (double)s1[1] / s1[0] == (double)s2[1] / s2[0];
    }

}
