package love.pangteen.problems.kuaishou.internship_240508;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/10 11:12
 **/
public class DestroyMonster {

    /**
     * 二维前缀和，空间复杂度比较勉强。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        int[][] monsters = new int[n][3];
        int mx = 0, my = 0;
        for(int i = 0; i < n; ++ i){
            monsters[i][0] = scanner.nextInt();
            monsters[i][1] = scanner.nextInt();
            monsters[i][2] = scanner.nextInt();
            mx = Math.max(mx, monsters[i][0]);
            my = Math.max(my, monsters[i][1]);
        }
        int[][] preSum = new int[mx + 1][my + 1];
        for(int i = 0; i < n; ++ i){
            preSum[monsters[i][0]][monsters[i][1]] = monsters[i][2];
        }
        for(int i = 1; i <= mx; ++ i){
            for(int j = 1; j <= my; ++ j){
                preSum[i][j] += preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
            }
        }
        int ans = 0;
        for(int i = w; i <= mx; ++ i){
            for(int j = h; j <= my; ++ j){
                int sum = preSum[i][j] - preSum[i - w][j] - preSum[i][j - h] + preSum[i - w][j - h];
                ans = Math.max(ans, sum);
            }
        }
        System.out.println(ans);
    }
}
