package love.pangteen.problems.alibaba.cloud_240512;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/12 22:55
 **/
public class _01Sequence {

    /**
     * 前缀和。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        String s = scanner.next();
        int[][] pre = new int[n + 1][2];
        for(int i = 0; i < 2; ++ i){
            for(int j = 0; j < n; ++ j){
                if(i == 0){
                    if((j & 1) == 0 ^ s.charAt(j) == '0') pre[j + 1][i] = 1;
                } else {
                    if((j & 1) == 0 ^ s.charAt(j) == '1') pre[j + 1][i] = 1;
                }
            }
        }
        for(int i = 0; i < n; ++ i){
            for(int j = 0; j < 2; ++ j){
                pre[i + 1][j] += pre[i][j];
            }
        }
        while(q -- > 0){
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int ans = Math.min((pre[r][0] - pre[l - 1][0]), (pre[r][1] - pre[l - 1][1]));
            System.out.println(ans);
        }
    }
}
