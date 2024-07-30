package love.pangteen.problems.iflytek.offer_240727;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/30 14:24
 **/
public class Base {

    /**
     * 简单模拟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 0;
        for(int i = 2; i <= 36; ++ i){
            int now = n;
            int cnt = 0;
            while(now > 0){
                if(now % i == 1) ++ cnt;
                now /= i;
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }
}
