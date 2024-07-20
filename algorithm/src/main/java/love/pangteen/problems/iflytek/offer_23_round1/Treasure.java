package love.pangteen.problems.iflytek.offer_23_round1;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 15:50
 **/
public class Treasure {

    /**
     * 构造。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] vis = new boolean[n + 1][m + 1];
        char[] res = {'D', 'S', 'A', 'W'};
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 1, y = 1;
        System.out.println("1 1");
        while(true){
            vis[x][y] = true;
            boolean findWays = false;
            for(int i = 0; i < 4; ++ i){
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if(nx >= 1 && nx <= n && ny >= 1 && ny <= m && !vis[nx][ny]){
                    x = nx; y = ny;
                    findWays = true;
                    System.out.print(res[i]);
                    break;
                }
            }
            if(!findWays) break;
        }
        System.out.println();
    }
}
