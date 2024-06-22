package love.pangteen.problems.bilibili.internship_240420;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/6 22:25
 **/
public class FindCow {

    /**
     * 推公式。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        System.out.println((n - 1) * n + 1);
    }
}
