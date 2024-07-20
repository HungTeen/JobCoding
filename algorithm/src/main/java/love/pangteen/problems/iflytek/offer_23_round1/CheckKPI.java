package love.pangteen.problems.iflytek.offer_23_round1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 15:41
 **/
public class CheckKPI {

    /**
     * 排序。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t -- > 0){
            int n = in.nextInt();
            int[] f = new int[n];
            int sum = 0;
            for(int i = 0; i < n; ++ i) {
                f[i] = in.nextInt();
                sum += f[i];
            }
            Arrays.sort(f);
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int mid = n >> 1;
            int median = (n & 1) == 0 ? (f[mid] + f[mid - 1]) / 2 : f[mid];
            int mean = sum / n;
            int value = (sum - f[0] - f[n - 1]) / (n - 2);
            System.out.println((median >= a || mean >= b || value >= c) ? "Yes" : "No");
        }
    }
}
