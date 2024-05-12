package main.java.problems.bytedance.internship_240508;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 16:53
 **/
public class HidingPermutation {

    /**
     * 暴力。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n - 1];
        for(int i = 0; i < n - 1; ++ i){
            a[i] = scanner.nextInt();
        }
        int[] p = new int[n];
        for(int i = 1; i <= n; ++ i){
            Set<Integer> set = new HashSet<>();
            set.add(i);
            p[0] = i;
            boolean flag = true;
            for(int j = 0; j < n - 1; ++ j){
                int x = a[j] - p[j];
                if(x > 0 && x <= n && !set.contains(x)){
                    p[j + 1] = x;
                    set.add(x);
                }else{
                    flag = false;
                    break;
                }
            }
            if(flag){
                for(int j = 0; j < n; ++ j){
                    System.out.print(p[j] + (j == n - 1 ? "\n" : " "));
                }
                return;
            }
        }
        System.out.println(-1);
    }
}
