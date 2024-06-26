package love.pangteen.problems.net_ease.internship_240621;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/26 20:18
 **/
public class PlanetInfluence {

    /**
     * 二维差分。
     */
    public static void main(String[] args) {
        final int N = 1005, O = 501;
        int[][] pre = new int[N][N];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 0; i < n; ++ i){
            int x = scanner.nextInt() + O;
            int y = scanner.nextInt() + O;
            int r = scanner.nextInt();
            int lx = Math.max(1, x - r), rx = Math.min(O << 1, x + r) + 1;
            int ly = Math.max(1, y - r), ry = Math.min(O << 1, y + r) + 1;
            pre[lx][ly] ++;
            pre[lx][ry] --;
            pre[rx][ly] --;
            pre[rx][ry] ++;
        }
        for(int i = 1; i < N; ++ i){
            for(int j = 1; j < N; ++ j){
                pre[i][j] += pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1];
            }
        }
        int q = scanner.nextInt();
        while(q -- > 0){
            int x = scanner.nextInt() + O;
            int y = scanner.nextInt() + O;
            System.out.println(pre[x][y]);
        }
    }
}
