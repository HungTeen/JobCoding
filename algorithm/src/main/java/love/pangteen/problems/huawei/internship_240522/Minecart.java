package love.pangteen.problems.huawei.internship_240522;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/23 10:53
 **/
public class Minecart {

    /**
     * 优先队列+BFS，取巧在标记重复位置用三维。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] src = new int[2], dst = new int[2];
        int[][][] ans = new int[n][m][2];
        boolean[][] checked = new boolean[n][m];
        boolean[][] blocked = new boolean[n][m];
        int[][] costs = new int[n][m];
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < m; ++ j){
                String x = scanner.next();
                if(x.charAt(0) == 'S'){
                    src = new int[]{i, j};
                } else if(x.charAt(0) == 'E'){
                    dst = new int[]{i, j};
                } else if(x.charAt(0) == 'C'){
                    checked[i][j] = true;
                } else if(x.charAt(0) == 'B'){
                    blocked[i][j] = true;
                } else {
                    costs[i][j] = Integer.parseInt(x);
                }
                ans[i][j][0] = ans[i][j][1] = -1;
            }
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(t -> t[2]));
        q.add(new int[]{src[0], src[1], 0, 0});
        ans[src[0]][src[1]][0] = 0;
        while(! q.isEmpty()){
            var tmp = q.poll();
            int x = tmp[0], y = tmp[1], cost = tmp[2];
            boolean check = (tmp[3] == 1);
            for(int i = 0; i < 4; ++ i){
                int tx = x + dirs[i][0];
                int ty = y + dirs[i][1];
                if(tx >= 0 && tx < n && ty >= 0 && ty < m && ! blocked[tx][ty]){
                    check |= (checked[tx][ty]);
                    int t = check ? 1 : 0;
                    if(ans[tx][ty][t] < 0){
                        ans[tx][ty][t] = cost + costs[tx][ty];
                        q.add(new int[]{tx, ty, ans[tx][ty][t], t});
                    }
                }
            }
        }
        System.out.println(ans[dst[0]][dst[1]][1]);
    }
}
