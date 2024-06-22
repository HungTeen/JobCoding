package love.pangteen.problems.bytedance.internship_240508;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 17:12
 **/
public class HorseJump {

    /**
     * 类似Dijkstra算法。
     */
    public static void main(String[] args) {
        int[][] dirs = new int[][]{{1, 2}, {2, 1}, {1, -2}, {2, -1}, {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}};
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] cost = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < m; ++ j){
                cost[i][j] = scanner.nextInt();
            }
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        q.add(new int[]{0, 0, cost[0][0]});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            if(vis[x][y]){
                continue;
            }
            vis[x][y] = true;
            if(x == n - 1 && y == m - 1){
                System.out.println(cur[2]);
                return;
            }
            for(int[] dir : dirs){
                int nx = x + dir[0], ny = y + dir[1];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny]){
                    q.add(new int[]{nx, ny, cur[2] + cost[nx][ny]});
                }
            }
        }
        System.out.println(-1);
    }
}
