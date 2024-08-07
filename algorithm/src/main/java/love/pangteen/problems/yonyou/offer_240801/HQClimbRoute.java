package love.pangteen.problems.yonyou.offer_240801;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/6 20:33
 **/
public class HQClimbRoute {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), r = sc.nextInt();
        int[] scores = new int[n], heights = new int[n], rests = new int[n];
        Arrays.setAll(scores, e -> sc.nextInt());
        Arrays.setAll(heights, e -> sc.nextInt());
        Arrays.setAll(rests, e -> sc.nextInt());

        int ma = 0;
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> n_path = new ArrayList<>();
            n_path.add(i);
            int ret = search1(scores, heights, rests, i, 1, 1, n, m, r, n_path);
            if (ret != -1 && ret > ma) {
                ma = ret;
                path = n_path;
            }
        }
        System.out.println(ma);

        for (int i=0; i<path.size(); i++) {
            System.out.print(path.get(i)+1);
            if (i < path.size()-1) {
                System.out.print(' ');
            }
        }
        System.out.print("\n");

        for (int i = 0; i < path.size(); i++) {
            if (i + r >= path.size()) {
                System.out.print(0);
            } else {
                int j = Math.min(i + r, path.size() - 1);
                while (j >= i && rests[path.get(j)] == 0) {
                    j--;
                }
                while (i < j) {
                    System.out.printf("%d ", 0);
                    i++;
                }
                System.out.print(1);
            }
            if (i < path.size() - 1) {
                System.out.printf(" ");
            }
        }
        System.out.print("\n");
    }

    static int search1(int[] scores, int[] heights, int[] rests, int pos, int a, int b, int n, int m, int r, List<Integer> path) {
        if (b > r && rests[pos] == 0) {
            return -1;
        }

        if (a >= m) {
            return scores[pos];
        }

        int res = scores[pos], nb = (rests[pos] == 1) ? 1 : b + 1;
        List<Integer> n_path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (heights[i] > heights[pos]) {
                List<Integer> n_n_path = new ArrayList<>();
                n_n_path.add(i);
                int ret = search1(scores, heights, rests, i, a + 1, nb, n, m, r, n_n_path);
                if (ret != -1 && ret + scores[pos] > res) {
                    res = ret + scores[pos];
                    n_path = n_n_path;
                }
            }
        }
        path.addAll(n_path);
        return res;
    }
}
