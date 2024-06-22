package love.pangteen.problems.g_bits.internship_240516;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 17:04
 **/
public class WhoIsBig {

    /**
     * 进制转换。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int m2 = scanner.nextInt();
        n1 = scanner.nextInt();
        n2 = scanner.nextInt();
        int x = parse(n1, m1);
        int y = parse(n2, m2);
        System.out.println(x + "," + y);
        System.out.println((x == y ? '=' : (x < y ? '<' : '>')));
    }

    private static int parse(int x, int q){
        int res = 0;
        int mult = 1;
        while(x > 0){
            res += mult * (x % 10);
            x /= 10;
            mult *= q;
        }
        return res;
    }
}
