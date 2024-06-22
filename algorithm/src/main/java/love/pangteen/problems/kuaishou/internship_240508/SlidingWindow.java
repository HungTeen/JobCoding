package love.pangteen.problems.kuaishou.internship_240508;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/9 22:09
 **/
public class SlidingWindow {

    /**
     * 滑动窗口。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Deque<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n; ++ i){
            int x = scanner.nextInt();
            while(!q.isEmpty() && i - q.getFirst()[1] >= k){
                q.pollFirst();
            }
            while(!q.isEmpty() && q.getLast()[0] < x){
                q.pollLast();
            }
            q.addLast(new int[]{x, i});
            if(i >= k - 1){
                System.out.print(q.getFirst()[0] + (i == n - 1 ? "\n" : " "));
            }
        }
    }
}
