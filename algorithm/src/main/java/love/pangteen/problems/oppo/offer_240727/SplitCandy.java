package love.pangteen.problems.oppo.offer_240727;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 15:57
 **/
public class SplitCandy {

    /**
     * 找规律。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] f = new int[3];
        for(int i = 0; i < 3; ++ i){
            f[i] = in.nextInt();
        }
        Arrays.sort(f);
        int cnt = in.nextInt();
        int k = in.nextInt();
        int maxCnt = 1 + (cnt - k);
        System.out.println(((f[1] != f[2] && maxCnt < f[2]) ? "YES" : "NO"));
    }
}
