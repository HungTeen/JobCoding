package love.pangteen.problems.iflytek.offer_240629;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/17 22:18
 **/
public class EvenOddSequence {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long ans = 1;
        for (int i = 2; i <= n; ++i) {
            if ((i & 1) == 0) {
                ans = (ans + 1) % MOD;
            } else {
                ans = (ans << 1) % MOD;
            }
        }
        System.out.println(ans);
    }
}
