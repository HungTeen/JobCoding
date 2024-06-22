package love.pangteen.problems.ant.internship_240413;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/7 22:36
 **/
public class CuttingLine {

    /**
     * 这题当前解法应该会超时，目前不知道最优解。
     * DP，复杂度O(n * sum)。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int sum = 0;
        for(int i = 0; i < n; ++ i){
            a[i] = scanner.nextInt();
            sum += a[i];
        }
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for(int i = 0; i < n; ++ i){
            for(int j = sum; j >= a[i]; -- j){
                dp[j] = dp[j] | dp[j - a[i]];
            }
        }
        int ans = 1;
        for(int i = 2; i < sum; ++ i){
            if(sum % i == 0){
                if(dp[i]){
                    ans = Math.max(ans, i);
                }
            }
        }
        System.out.println(ans);
    }
}
