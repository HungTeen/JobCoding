package love.pangteen.problems.pdd.offer_240811;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/11 21:11
 **/
public class PriorityTravel {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] p = new int[n];
        int[] x = new int[n];
        int[] d = new int[n];
        int[] f = new int[n];
        for(int i = 0; i < n; ++ i){
            p[i] = in.nextInt() - 1;
            x[i] = in.nextInt();
            d[i] = in.nextInt();
            f[p[i]] = i;
        }
        int day = 0;
        for(int i = 0; i < n; ++ i){
            int k = (day - x[i]) / d[i];
            if(k < 0) k = 0;
            if(x[i] + k * d[i] <= day){
                k ++;
            }
            day = x[i] + k * d[i];
        }
        System.out.println(day);
    }

}
