package love.pangteen.problems.ant.internship_240413;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/7 22:29
 **/
public class JudgeShape {

    /**
     * 找到关键区别即可。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        while (q -- > 0){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String[] image = new String[n];
            for (int i = 0; i < n; i++) {
                image[i] = scanner.next();
            }
            int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
            int res = 0;
            for(int i = 0; i < n; ++ i){
                for(int j = 0; j < m; ++ j){
                    if(image[i].charAt(j) == '.'){
                        continue;
                    }
                    int cnt = 0;
                    for(int k = 0; k < 4; ++ k){
                        int x = i + dirs[k][0];
                        int y = j + dirs[k][1];
                        if(x >= 0 && x < n && y >= 0 && y < m && image[x].charAt(y) == '*'){
                            cnt ++;
                        }
                    }
                    res = Math.max(res, cnt);
                }
            }
            System.out.println(res == 2 ? "L" : "T");
        }
    }
}
