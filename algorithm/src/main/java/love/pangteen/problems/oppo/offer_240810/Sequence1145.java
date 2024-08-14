package love.pangteen.problems.oppo.offer_240810;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/13 22:29
 **/
public class Sequence1145 {

    /**
     * 动态维护4左边的11数量和右边的5数量。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] s = in.next().toCharArray();
        long cnt5 = 0, cnt11 = 0;
        long ans = 0;
        for(int i = 0; i < n; ++ i){
            if(s[i] == '5') ++ cnt5;
        }
        for(int i = 0; i < n; ++ i){
            if(s[i] == '5') -- cnt5;
            if(i > 0 && s[i] == '1' && s[i - 1] == '1') ++ cnt11;
            if(s[i] == '4'){
                ans += cnt11 * cnt5;
            }
        }
        System.out.println(ans);
    }
}
