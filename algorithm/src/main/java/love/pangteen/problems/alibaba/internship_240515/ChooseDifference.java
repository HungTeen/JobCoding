package love.pangteen.problems.alibaba.internship_240515;

import java.util.Map;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 16:46
 **/
public class ChooseDifference {

    /**
     * 桶排序，复杂度O(m^2)。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = 4000;
        int[] bin = new int[m + 1];
        for(int i = 0; i < n; ++ i){
            ++ bin[scanner.nextInt()];
        }
        for(int i = 1; i <= m; ++ i) bin[i] += bin[i - 1];
        int maxCnt = 1;
        int res = 0;
        for(int k = 1; k <= n; ++ k){
            while(k > maxCnt){
                ++ res;
                for(int i = 1 + res; i <= m; ++ i){
                    maxCnt = Math.max(maxCnt, bin[i] - bin[i - res - 1]);
                }
            }
            System.out.println(res);
        }
    }
}
