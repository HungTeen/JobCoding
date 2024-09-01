package love.pangteen.problems.tencent.offer_240829;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoundPrintMatrix {

    public static void main(String[] args) {
        problem1();
    }

    /*
    样例一：
    3 3
    1 2 3
    4 5 6
    7 8 9
    样例二：
     3 4
     1 2 3 4
     5 6 7 8
     9 10 11 12
     */
    private static void problem1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < m; ++ j){
                matrix[i][j] = in.nextInt();
            }
        }
        List<Integer> ans = new ArrayList<>();
        boolean[][] vis = new boolean[n][m];
        int dir = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        while(true){
            vis[x][y] = true;
            ans.add(matrix[x][y]);
            int tx = x + dirs[dir][0], ty = y + dirs[dir][1];
            if(tx >= 0 && tx < n && ty >= 0 && ty < m && !vis[tx][ty]){
                x = tx; y = ty;
            } else {
                dir = (dir + 1) % 4;
                tx = x + dirs[dir][0];
                ty = y + dirs[dir][1];
                if(tx >= 0 && tx < n && ty >= 0 && ty < m && !vis[tx][ty]){
                    x = tx; y = ty;
                } else break;
            }
        }
        for(int i = 0; i < ans.size(); ++ i){
            System.out.print(ans.get(i) + (i == ans.size() - 1 ? "\n" : " "));
        }
    }

}