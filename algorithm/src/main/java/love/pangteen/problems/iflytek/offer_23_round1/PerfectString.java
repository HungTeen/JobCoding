package love.pangteen.problems.iflytek.offer_23_round1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 16:18
 **/
public class PerfectString {

    /**
     * DP。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();
        int[] maxPre = new int[26];
        Arrays.fill(maxPre, -1);
        int pre = 0, now = -1;
        for(int i = 1; i <= n; ++ i){
            char x = s.charAt(i - 1);
            int v = x - 'a';
            now = maxPre[v] == -1 ? -1 : maxPre[v] + 1;
            maxPre[v] = Math.max(maxPre[v], pre);
            pre = now;
        }
        System.out.println(now);
    }
}
