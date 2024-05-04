package main.java.problems.shopee.internship_0429;

import main.java.core.InputGenerator;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 15:43
 **/
public class MoreHero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] cost = new int[n];
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            cost[i] = scanner.nextInt();
            idx[i] = i;
        }
        int k = scanner.nextInt();
        Arrays.sort(idx, Comparator.comparingInt(o -> cost[o]));
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; ++ i){
            if(cost[idx[i]] <= k){
                k -= cost[idx[i]];
                list.add(i);
            }
        }
        list.sort(Integer::compare);
        for(int i = 0; i < list.size(); ++ i){
            System.out.print(cost[list.get(i)]);
            if(i != list.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
