package love.pangteen.problems.bilibili;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/11 22:01
 **/
public class STDSameNodeLength {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] arr = new Integer[n];
        Arrays.fill(arr, null);
        for(int i = 0; i < n; ++ i){
            String x = scanner.next();
            if(! x.equals("null")){
                arr[i] = Integer.valueOf(x);
            }
        }
        int ans = 0;
        for(int i = 0; i < n; ++ i){
            boolean[] vis = new boolean[n];
            Deque<int[]> q = new ArrayDeque<>();
            q.addLast(new int[]{i, 0});
            vis[i] = true;
            while(!q.isEmpty()){
                var cur = q.pollFirst();
                int now = cur[0];
                ans = Math.max(ans, cur[1]);
                int[] nxtPs = new int[]{now * 2 + 1, now * 2 + 2, (now - 1) / 2};
                for(var nxt : nxtPs){
                    if(nxt >= 0 && nxt < n && !vis[nxt] && arr[nxt] != null && arr[nxt].equals(arr[now])){
                        vis[nxt] = true;
                        q.addLast(new int[]{nxt, cur[1] + 1});
                    }
                }
            }
        }
    }
}
