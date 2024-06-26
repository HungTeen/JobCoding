package love.pangteen.problems.net_ease.internship_240621;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/26 20:10
 **/
public class BuildHouse {

    /**
     * 不知道N的范围，多维背包。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        long[][][] dp = new long[A + 1][B + 1][C + 1];
        for(int i = 0; i < n; ++ i){
            int[] f = new int[4];
            for(int j = 0; j < 4; ++ j) f[j] = scanner.nextInt();
            for(int j = A; j >= f[0]; -- j){
                for(int k = B; k >= f[1]; -- k){
                    for(int l = C; l >= f[2]; -- l){
                        dp[j][k][l] = Math.max(dp[j][k][l], dp[j - f[0]][k - f[1]][l - f[2]] + f[3]);
                    }
                }
            }
        }
        System.out.println(dp[A][B][C]);
    }

    /*
    4 2 2 2
    2 0 0 5
    1 1 1 1
    0 1 0 4
    0 0 1 1
     */
}
