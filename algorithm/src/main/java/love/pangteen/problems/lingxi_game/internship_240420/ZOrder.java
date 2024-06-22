package love.pangteen.problems.lingxi_game.internship_240420;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/6 22:54
 **/
public class ZOrder {

    /**
     * 大数 & 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while(n -- > 0){
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 32; ++ i){
                sb.append((y & 1) == 0 ? "0" : "1");
                sb.append((x & 1) == 0 ? "0" : "1");
                x >>= 1;
                y >>= 1;
            }
            sb.reverse();
            BigInteger bigInteger = new BigInteger(sb.toString(), 2);
            System.out.println(bigInteger);
        }
    }
}
