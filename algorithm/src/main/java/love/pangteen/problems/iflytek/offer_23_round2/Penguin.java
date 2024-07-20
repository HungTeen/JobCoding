package love.pangteen.problems.iflytek.offer_23_round2;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 15:21
 **/
public class Penguin {

    /**
     * 找两侧最小值相加。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++ i){
            int x = in.nextInt();
            if(x == -1){
                ans = min;
                min = Integer.MAX_VALUE;
            } else {
                min = Math.min(min, x);
            }
        }
        System.out.println(ans + min);
    }
}
