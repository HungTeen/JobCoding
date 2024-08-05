package love.pangteen.problems.oppo.offer_240727;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/30 16:27
 **/
public class ThreeXYK {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int k = in.nextInt();
        if(k == 1) {
            System.out.println(x - y);
            return;
        }
        int cnt = 0;
        while(x > y){
            int left = x % k;
            if(left == 0){
                if(x / k < y){
                    cnt += (x - y);
                    break;
                } else {
                    x /= k;
                    ++ cnt;
                }
            } else {
                x -= left;
                cnt += left;
            }
        }
        System.out.println(cnt);
    }
}
