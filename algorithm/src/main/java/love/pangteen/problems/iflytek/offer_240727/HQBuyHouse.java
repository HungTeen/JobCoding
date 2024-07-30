package love.pangteen.problems.iflytek.offer_240727;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/29 22:55
 **/
public class HQBuyHouse {

    static class Pair {
        int a, b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] golds = new int[n];
        for (int i=0; i<n; i++) {
            golds[i] = sc.nextInt();
        }
        // price、score
        Pair[] ps = new Pair[m];
        Arrays.setAll(ps, e -> new Pair());
        for (int i=0; i<m; i++) {
            ps[i].b = sc.nextInt();
            ps[i].a = sc.nextInt();
        }

        Arrays.sort(golds);
        Arrays.sort(ps, (i, j) -> {
            return i.a - j.a;
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>((i, j) -> j-i);
        int i=0, j=0, ans =0;
        for (; i<n && j < m; i++) {
            // 买的起的入队
            while (j < m && golds[i] >= ps[j].a) {
                queue.offer(ps[j].b);
                j++;
            }
            // 出队
            if (queue.size() > 0) {
                ans += queue.poll();
            }
        }
        for (; i<n && queue.size() > 0; i++) {
            ans += queue.poll();
        }
        System.out.println(ans);
    }

}
