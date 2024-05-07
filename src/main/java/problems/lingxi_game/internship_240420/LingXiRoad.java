package main.java.problems.lingxi_game.internship_240420;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/7 18:36
 **/
public class LingXiRoad {

    /**
     * 并查集。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        while(k -- > 0){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int st = scanner.nextInt();
            int ed = scanner.nextInt();
            List<int[]> edges = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                edges.add(new int[]{u, v, w});
            }
            edges.sort(Comparator.comparingInt(o -> o[2]));
            int minV = 1, maxV = Integer.MAX_VALUE;
            for(int i = 0; i < m; ++ i){
                int[] dad = new int[n + 1];
                for(int j = 0; j <= n; ++ j) dad[j] = j;
                int j;
                for(j = i; j < m; ++ j){
                    int fx = find(dad, edges.get(j)[0]);
                    int fy = find(dad, edges.get(j)[1]);
                    if(fx != fy) {
                        dad[fx] = fy;
                    }
                    if(find(dad, st) == find(dad, ed)){
                        break;
                    }
                }
                if(j != m){
                    if(1.0 * maxV / minV > 1.0 * edges.get(j)[2] / edges.get(i)[2]){
                        minV = edges.get(i)[2];
                        maxV = edges.get(j)[2];
                    }
                }
            }
            if(maxV == Integer.MAX_VALUE){
                System.out.println("%%%");
            } else {
                int gcd = gcd(minV, maxV);
                if(minV == gcd){
                    System.out.printf("%d\n", maxV / gcd);
                } else {
                    System.out.printf("%d/%d\n", maxV / gcd, minV / gcd);
                }
            }
        }
    }

    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int find(int[] dad, int x){
        return dad[x] == x ? x : (dad[x] = find(dad, dad[x]));
    }
}
