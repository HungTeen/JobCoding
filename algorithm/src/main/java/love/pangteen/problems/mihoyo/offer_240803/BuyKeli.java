package love.pangteen.problems.mihoyo.offer_240803;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/8 21:34
 **/
public class BuyKeli {

    /**
     * 暴力枚举，复杂度O(2^n * (n + k))
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int[] w = new int[n], v = new int[n], limit = new int[k];
        for(int i = 0; i < n; ++ i){
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }
        for(int i = 0; i < k; ++ i){
            limit[i] |= (1 << in.nextInt() - 1);
            limit[i] |= (1 << in.nextInt() - 1);
        }
        long ans = 0;
        for(int i = 1; i < (1 << n); ++ i){
            boolean valid = true;
            for(int j = 0; j < k; ++ j){
                if((i & limit[j]) == limit[j]) {
                    valid = false; break;
                }
            }
            if(! valid) continue;
            long sum = 0, weight = 0;
            for(int j = 0; j < n; ++ j){
                if(((i >> j) & 1) == 1){
                    sum += v[j];
                    weight += w[j];
                }
            }
            if(weight <= m){
                ans = Math.max(ans, sum);
            }
        }
        System.out.println(ans);
    }
}
