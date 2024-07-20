package love.pangteen.problems.iflytek.offer_23_round3;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/20 14:54
 **/
public class BeautifulSequence {

    /**
     * 规律。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 0; i < n; ++ i){
            System.out.print((n - i) + (i == n - 1 ? "\n" : " "));
        }
    }
}
