package love.pangteen.problems.ant.internship_240511;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/28 12:18
 **/
public class AntiElement {

    /**
     * 贪心，先把负数变正，再尽量变0，最后考虑正数。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int a = 0, b = 0;
        int zero = 0;
        for(int i = 0; i < n; ++ i) {
            int x = scanner.nextInt();
            if (x > 0) ++a;
            else if (x < 0) ++b;
            else ++ zero;
        }
        if(k <= b){
            System.out.println(a + b);
        } else {
            System.out.println(a + b - 2 * (Math.max(0, k - b - zero)));
        }
    }
}
