package love.pangteen.problems.pdd.offer_240811;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/11 21:32
 **/
public class HQPriorityTravel {

    static class Pair {
        long p, x, d;
        Pair(long p, long x) {
            this(p, x, 0);
        }
        Pair(long p, long x, long d) {
            this.p = p;
            this.x = x;
            this.d = d;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pair[] views = new Pair[n];
        for (int i=0; i<n; i++) {
            long p = sc.nextLong();
            long x = sc.nextLong();
            long d = sc.nextLong();
            views[i] = new Pair(p, x, d);
        }
        Arrays.sort(views, (i, j) -> Long.compare(i.p, j.p));
        long day = 0;
        for (int i=0; i<n; i++) {
            if (views[i].x > day) {
                day = views[i].x;
            } else {
                views[i].x += (day - views[i].x) / views[i].d * views[i].d;
                if(views[i].x <= day) {
                    views[i].x += views[i].d;
                }
                day = views[i].x;
            }
        }
        System.out.println(day);
    }
}
