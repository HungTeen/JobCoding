package love.pangteen.problems.oppo.offer_240810;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/13 22:19
 **/
public class AcrossRiver {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int lastStable = -1;
        int ans = 1;
        for(int i = 0; i < n; ++ i){
            int x = in.nextInt();
            if(x == 1){
                ans = Math.max(ans, i - lastStable);
                lastStable = i;
            }
        }
        ans = Math.max(ans, n - lastStable);
        System.out.println(ans);
    }
}
