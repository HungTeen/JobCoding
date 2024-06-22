package love.pangteen.problems.bilibili.internship_240420;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/6 22:41
 **/
public class FlipString {

    /**
     * 找规律。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();
        System.out.print(s.substring(k - 1));
        StringBuilder sb = new StringBuilder(s.substring(0, k - 1));
        if(((n - k + 1) & 1) == 1){
            sb.reverse();
        }
        System.out.println(sb.toString());
    }

}
