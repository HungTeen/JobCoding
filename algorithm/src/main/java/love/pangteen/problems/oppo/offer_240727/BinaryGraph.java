package love.pangteen.problems.oppo.offer_240727;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/30 17:10
 **/
public class BinaryGraph {

    /**
     * BFS，找规律。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer>[] edges = new List[n + 1];
        for(int i = 0; i <= n; ++ i){
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < n - 1; ++ i){
            int x = in.nextInt();
            int y = in.nextInt();
            edges[x].add(y);
            edges[y].add(x);
        }
        int[] color = new int[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        color[1] = 1;
        int cnt1 = 0;
        while(!q.isEmpty()){
            int now = q.pollFirst();
            int currentColor = color[now];
            if(currentColor == 1) ++ cnt1;
            for(var y : edges[now]){
                if(color[y] == 0){
                    q.add(y);
                    color[y] = (currentColor == 1 ? 2 : 1);
                }
            }
        }
        System.out.println(cnt1 * (n - cnt1) - (n - 1));
    }
}
