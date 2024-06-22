package love.pangteen.problems.lingxi_game.internship_240420;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/7 18:12
 **/
public class Maze {

    /**
     * 读题 & 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int[] c = new int[n];
        int minC = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++ i){
            c[i] = scanner.nextInt();
            minC = Math.min(minC, c[i]);
        }
        for(int i = 1; i < n; ++ i){

            if((c[i] - c[i - 1]) % x != 0){
                System.out.println(-1);
                return;
            }
        }
        int ans = minC - x;
        System.out.println(ans <= 0 ? -1 : ans);
    }
}
