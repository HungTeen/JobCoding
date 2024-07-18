package love.pangteen.problems.iflytek.offer_240706;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/18 21:02
 **/
public class RangeCM {

    /**
     * 数学。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t -- > 0){
            long a = scanner.nextInt();
            long b = scanner.nextInt();
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            long ans = get(l, r, a) + get(l, r, b) - get(l, r, a * b / gcd(a, b));
            System.out.println(ans);
        }
    }

    private static long get(long x, long y, long p){
        long st = (x + p - 1) / p;
        long ed = (y) / p;
        return ed - st + 1;
    }

    private static long gcd(long x, long y){
        return y == 0 ? x : gcd(y, x % y);
    }
}
