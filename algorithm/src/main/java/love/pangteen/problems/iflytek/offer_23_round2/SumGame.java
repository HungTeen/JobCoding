package love.pangteen.problems.iflytek.offer_23_round2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 15:29
 **/
public class SumGame {

    /**
     * 打标记。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        Arrays.setAll(f, (i) -> in.nextInt());
        int m = in.nextInt();
        int ans = m - 1;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; ++ i){
            if(f[i] <= 0 && f[i] >= m) continue;
            int x = f[i], y = m - f[i];
            if(! set.contains(x) && ! set.contains(y)){
                ans -= (x == y ? 1 : 2);
                set.add(x);
                set.add(y);
            }
        }
        System.out.println(ans);
    }
}
