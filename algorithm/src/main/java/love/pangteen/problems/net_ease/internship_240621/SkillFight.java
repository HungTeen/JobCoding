package love.pangteen.problems.net_ease.internship_240621;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/26 19:45
 **/
public class SkillFight {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][][] f = new int[2][2][5];
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 5; ++k) {
                    f[i][j][k] = scanner.nextInt();
                }
            }
        }
        int[] ps = new int[2];
        int fi = 0;
        while (ps[0] < 5 && ps[1] < 5) {
            int se = fi ^ 1;
            int p = ps[fi], q = ps[se];
            int h1 = f[fi][1][p], h2 = f[se][1][q];
            int a1 = f[fi][0][p], a2 = f[se][0][q];
            int r1 = (h2 + a1 - 1) / a1; // 需要多少轮才能击杀。
            int r2 = (h1 + a2 - 1) / a2;
            if (r1 <= r2) {
                f[0][1][p] -= (r1 - 1) * a2;
                ++ ps[se];
            } else {
                f[0][1][q] -= r1 * a1;
                ++ ps[fi];
            }
            fi = se;
        }
        System.out.println(ps[0] + ", " + ps[1]);
        System.out.println((ps[0] == 5 ? "lose" : "win"));
        System.out.println((ps[0] == 5 ? 5 - ps[1] : 5 - ps[0]));
    }
}
