package love.pangteen.problems.iflytek.offer_240706;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/18 20:56
 **/
public class Cube {

    /**
     * 贪心。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] f = new int[n];
        for(int i = 0; i < n; ++ i){
            int v = 0;
            for(int j = 0; j < 6; ++ j){
                v = Math.max(v, scanner.nextInt());
            }
            f[i] = v;
        }
        Arrays.sort(f);
        if(f[n - 1] == 0){
            System.out.println(0);
        } else {
            for(int i = n - 1; i >= 0; -- i){
                System.out.print(f[i]);
            }
            System.out.println();
        }
    }
}
