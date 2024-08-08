package love.pangteen.problems.mihoyo.offer_240803;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/8 21:45
 **/
public class DeleteEdge {

    /**
     * 拓扑排序 & 博弈论。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t -- > 0){
            int n = in.nextInt();
            int x = in.nextInt() - 1;
            List<Integer>[] edges = new List[n];
            Arrays.setAll(edges, i -> new ArrayList<>());
            int[] degree = new int[n];
            for(int i = 0; i < n; ++ i){
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                degree[a] ++; degree[b] ++;
                edges[a].add(b);
                edges[b].add(a);
            }
            if(degree[x] == 1) {
                System.out.println("Xiaoyo");
                continue;
            }
            Deque<Integer> q = new ArrayDeque<>();
            for(int i = 0; i < n; ++ i){
                if(degree[i] == 1){
                    q.add(i);
                }
            }
            int cnt = 0;
            while(! q.isEmpty()){
                int now = q.pollFirst();
                ++ cnt;
                if(now == x) continue;
                for(int y : edges[now]){
                    if(-- degree[y] == 1){
                        q.add(y);
                    }
                }
            }
            if(degree[x] == 1){
                System.out.println((cnt & 1) == 1 ? "Xiaoyo" : "Pyrmont");
            } else {
                System.out.println("Draw");
            }
        }
    }
}
