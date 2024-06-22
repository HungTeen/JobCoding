package love.pangteen.problems.ctrip.internship_240520;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 21:27
 **/
public class MergeMagicBall {

    /**
     * 优先队列。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for(int i = 0; i < n; ++ i){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            q.add(new int[]{a, b});
        }
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int[] now = q.poll();
            int a = now[0], b = now[1];
            while(!q.isEmpty() && q.peek()[0] == a){
                b += q.poll()[1];
            }
            for(int i = 31; i >= 0; -- i){
                if(((b >> i) & 1) == 1){
                    if(i == 0){
                        ans.add(a);
                    } else {
                        q.add(new int[]{a + (i << 1), 1});
                    }
                }
            }
        }
        for(int i = 0; i < ans.size(); ++ i){
            System.out.print(ans.get(i) + (i == ans.size() - 1 ? "\n" : " "));
        }
    }
}
