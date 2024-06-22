package love.pangteen.problems.g_bits.internship_240516;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 17:15
 **/
public class DigitGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] f = new int[n];
        int[] pre = new int[n];
        for(int i = 0; i < n; ++ i){
            f[i] = scanner.nextInt();
            if(i > 0) pre[i] = pre[i - 1] + f[i];
        }
        int now = 0;
        int sum = 0;
        int ans = 0;
        for(int i = 0; i < n; ++ i){
            sum += f[i];
            if(sum >= now){
                ++ ans;
                now = f[i];
                sum = 0;
            }
        }
        System.out.println(ans);
    }
}
