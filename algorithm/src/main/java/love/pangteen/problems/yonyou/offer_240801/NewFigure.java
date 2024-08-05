package love.pangteen.problems.yonyou.offer_240801;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 20:18
 **/
public class NewFigure {

    /**
     * 数学，变成优先 3最佳。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int limit = in.nextInt();
        int n = in.nextInt();
        long ans = 1;
        int cnt3 = n / 3;
        int left = n % 3;
        if(n <= 3) {
            ans = (n - 1);
            cnt3 = 0;
        } else {
            if(left == 1 && cnt3 > 0){
                -- cnt3;
                ans *= 4;
            } else if(left == 2){
                ans *= 2;
            }
        }
        boolean exceed = (ans > limit);
        for(int i = 0; i < cnt3; ++ i){
            ans *= 3;
            if(ans > limit){
                exceed = true;
                break;
            }
        }
        System.out.println(exceed ? "true" : "false");
    }
}
