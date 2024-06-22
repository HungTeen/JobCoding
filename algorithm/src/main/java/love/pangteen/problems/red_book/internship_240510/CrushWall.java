package love.pangteen.problems.red_book.internship_240510;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/27 12:41
 **/
public class CrushWall {

    /**
     * 公式。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n = scanner.nextInt();
        double ans = 2 / n / (n - 1);
        System.out.printf("%.10f\n", ans);
    }
}
