package love.pangteen.problems.huawei.offer_240717;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/18 22:55
 **/
public class TravelBudget {

    /**
     * 优先队列。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int budget = scanner.nextInt();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            int cost = Integer.parseInt(s[0]);
            int times = Integer.parseInt(s[1]);
            q.add(new int[]{cost, times});
        }
        int ans = 0;
        while(budget > 0 && !q.isEmpty()){
            int[] now = q.poll();
            int count = Math.min(now[1], budget / now[0]);
//            if(count == 0) break;
            budget -= count * now[0];
            ans += count;
        }
        System.out.println(ans);
    }
}
