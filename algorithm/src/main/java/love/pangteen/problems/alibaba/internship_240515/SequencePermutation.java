package love.pangteen.problems.alibaba.internship_240515;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 16:02
 **/
public class SequencePermutation {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * 统计。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] bins = new int[n + 1];
        for(int i = 0; i < n; ++ i){
            int x = scanner.nextInt();
            if(x >= 1 && x <= n){
                ++ bins[x];
            }
        }
        long ans = 0;
        long res = 1;
        for(int i = 1; i <= n; ++ i){
            if(bins[i] > 0){
                res = (res * bins[i]) % MOD;
                ans = (ans + res) % MOD;
            } else break;
        }
        System.out.println(ans);
    }
}
