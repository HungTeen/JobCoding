package love.pangteen.problems.iflytek.offer_23_round2;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 15:25
 **/
public class ClimbMountain {

    /**
     * 能变就变。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; ++ i){
            a[i] = in.nextInt();
        }
        long ans = 0;
        for(int i = 0; i < n; ++ i){
            int x = in.nextInt();
            ans += Math.min(Math.abs(a[i] - x), Math.abs(a[i] + x));
        }
        System.out.println(ans);
    }
}
