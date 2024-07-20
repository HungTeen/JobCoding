package love.pangteen.problems.iflytek.offer_23_round3;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 15:13
 **/
public class SavePermutation {

    /**
     * 查找相同连续区域。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f1 = new int[n];
        int[] f2 = new int[n];
        int[] idx = new int[n + 1];
        for(int i = 0; i < n; ++ i){
            f1[i] = in.nextInt();
            idx[f1[i]] = i;
        }
        for(int i = 0; i < n; ++ i){
            f2[i] = in.nextInt();
        }
        long ans = 1L * n * (n + 1);
        for(int i = 0; i < n; ++ i){
            int cnt = 0;
            int st = idx[f2[i]];
            while(st < n && i < n && f1[st] == f2[i]){
                ++ i; ++ st; ++ cnt;
            }
            ans -= (cnt + 1) * cnt / 2;
            -- i;
        }
        System.out.println(ans);
    }
}
