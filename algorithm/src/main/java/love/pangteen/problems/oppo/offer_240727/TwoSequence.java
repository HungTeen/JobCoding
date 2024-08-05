package love.pangteen.problems.oppo.offer_240727;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 16:21
 **/
public class TwoSequence {

    /**
     * 二分 + 排序。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        int[] g = new int[n];
        for(int i = 0; i < n; ++ i) f[i] = in.nextInt();
        for(int i = 0; i < n; ++ i) g[i] = in.nextInt();
        Arrays.sort(f);
        Arrays.sort(g);
        int ans = n;
        for(int i = 0; i < n; ++ i){
            int l = 0, r = n - 1;
            int res = -1;
            while(l <= r){
                int mid = l + r >> 1;
                if(g[mid] >= f[i]){
                    res = mid; r = mid - 1;
                } else l = mid + 1;
            }
            ans = Math.max(ans, res == -1 ? 0 : n - res + i + 1);
        }
        System.out.println(ans);
    }
}
