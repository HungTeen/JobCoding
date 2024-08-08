package love.pangteen.problems.mihoyo.offer_240803;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/8 21:15
 **/
public class SimpleSwapNearby {

    /**
     * 直接考虑所有情况。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] f = new long[n];
        Arrays.setAll(f, i -> in.nextInt());
        long ans = 0;
        for(int i = 1; i < n; ++ i){
            for(int j = 1; j <= 2; ++ j){
                if(i - j >= 0) ans = Math.max(ans, f[i] * f[i - j]);
            }
        }
        System.out.println(ans);
    }

}
