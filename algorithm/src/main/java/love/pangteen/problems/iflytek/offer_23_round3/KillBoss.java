package love.pangteen.problems.iflytek.offer_23_round3;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 14:59
 **/
public class KillBoss {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        for(int i = 1; i < n; ++ i){
            int dif = s.charAt(i) - s.charAt(i - 1);
            k -= dif;
            if(k < 0){
                System.out.println("-1");
                return;
            }
        }
        System.out.println(k);
    }
}
