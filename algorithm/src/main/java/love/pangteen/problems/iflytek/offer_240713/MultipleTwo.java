package love.pangteen.problems.iflytek.offer_240713;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/13 22:49
 **/
public class MultipleTwo {

    /**
     * DP，但是二进制优化掉冗余项，时间复杂度O(nlognlogn)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] f = new int[n];
        for(int i = 0; i < n; ++ i){
            f[i] = scanner.nextInt();
        }
        List<int[]> pre = new ArrayList<>();
        List<int[]> now = new ArrayList<>();
        pre.add(new int[]{0, 0});
        for(int i = 0; i < n; ++ i){
            int tries = 0;
            int tmp = f[i];
            while(tmp > 0) {
                tmp >>= 1;
                tries ++;
                now.add(match(pre, tmp, tries));
            }
            tmp = f[i];
            for(int j = 0; j <= 20; ++ j){
                if(tmp > 2e5) break;
                now.add(match(pre, tmp, j));
                tmp <<= 1;
            }
            now.sort(Comparator.comparingInt(a -> a[0]));
            pre = now;
            now = new ArrayList<>();
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < pre.size(); ++ i){
            ans = Math.min(ans, pre.get(i)[1]);
        }
        System.out.println(ans);
    }

    private static int[] match(List<int[]> pre, int now, int mv){
        int minV = Integer.MAX_VALUE;
        for(int i = 0; i < pre.size(); ++ i){
            if(pre.get(i)[0] <= now){
                minV = Math.min(minV, pre.get(i)[1] + mv);
            } else break;
        }
        return new int[]{now, minV};
    }
}
