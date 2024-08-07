package love.pangteen.problems.iflytek.offer_240803;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/7 22:54
 **/
public class SwapNode {

    /**
     * 交换 & 排序。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        for(int i = 0; i < n; ++ i) f[i] = in.nextInt() - 1;
        for(int i = 0; i < n - 1; ++ i){
            in.nextInt();
            in.nextInt();
        }
        int cnt = 0;
        for(int i = 0; i < n; ++ i){
            while(f[i] != i){
                int j = f[i];
                int tmp = f[j];
                f[j] = f[i];
                f[i] = tmp;
                ++ cnt;
            }
        }
        System.out.println(cnt);
    }
}
