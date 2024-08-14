package love.pangteen.problems.oppo.offer_240810;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/13 22:24
 **/
public class OrSegment {

    /**
     * 简单贪心.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int now = 0;
        int cnt = 1;
        boolean flag = false;
        for(int i = 0; i < n; ++ i){
            int x = in.nextInt();
            if(x > k){
                flag = true;
            }
            if((now | x) > k){
                ++ cnt;
                now = x;
            } else {
                now |= x;
            }
        }
        System.out.println(flag ? -1 : cnt);
    }
}
