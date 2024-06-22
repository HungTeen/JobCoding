package love.pangteen.problems.alibaba.internship_240421;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 18:30
 **/
public class Convergence {

    /**
     * 不确定对不对。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if(a == 0) {
            System.out.println(-1);
            return;
        }
        double ans = 0;
        for(long i = 1; i <= 2e7; ++ i){
            ans += 1.0 / (a * i * i + b * i + c);
        }
        System.out.printf("%.9f\n", ans);
    }
}
