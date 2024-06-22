package love.pangteen.problems.alibaba.cloud_240421;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 19:05
 **/
public class Permutation {

    /**
     * 简单构造。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[] vis = new boolean[n + 1];
        int[] ans = new int[n];
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            int a =  scanner.nextInt();
            int sum = n + 1 + a;
            if((sum & 1) == 1){
                flag = false;
                break;
            }
            int x = sum >> 1;
            int y = n + 1 - x;
            if(vis[x]){
                if(vis[y]){
                    flag = false;
                    break;
                }
                vis[y] = true;
                ans[i] = y;
            } else {
                vis[x] = true;
                ans[i] = x;
            }
        }
        if(flag){
            for(int i = 0; i < n; ++ i){
                System.out.print(ans[i] + (i == n - 1 ? "\n" : " "));
            }
            for(int i = 0; i < n; ++ i){
                System.out.print((n + 1 - ans[i]) + (i == n - 1 ? "\n" : " "));
            }
        } else {
            System.out.println(-1);
        }
    }
}
