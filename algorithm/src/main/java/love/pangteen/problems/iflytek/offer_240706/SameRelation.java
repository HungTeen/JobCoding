package love.pangteen.problems.iflytek.offer_240706;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/18 21:24
 **/
public class SameRelation {

    /**
     * 找到连通点集，每个点都要和点集其他点双向连接。BFS。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean[] vis = new boolean[m + 1];
        List<Integer>[] edges = new List[m + 1];
        Set<String> set = new HashSet<>();
        for(int i = 0; i <= m; ++ i) edges[i] = new ArrayList<>();
        for(int i = 0; i < n; ++ i){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(x != y){
                edges[x].add(y);
                edges[y].add(x);
            }
            set.add(hash(x, y));
            vis[x] = vis[y] = true;
        }
        for(int i = 1; i <= m; ++ i) {
            if(! vis[i]) {
                System.out.println("no");
                return;
            }
        }
        for(int i = 1; i <= m; ++ i){
            if(vis[i]){
                Deque<Integer> q = new ArrayDeque<>();
                q.add(i);
                vis[i] = false;
                List<Integer> group = new ArrayList<>();
                while(!q.isEmpty()){
                    int now = q.pollFirst();
                    group.add(now);
                    for(int nxt : edges[now]){
                        if(vis[nxt]){
                            vis[nxt] = false;
                            q.addLast(nxt);
                        }
                    }
                }
                // 必须和之前的点集互相可达。
                for(int j = 0; j < group.size(); ++ j){
                    for(int k = j + 1; k < group.size(); ++ k){
                        int now = group.get(j);
                        int point = group.get(k);
                        if(! set.contains(hash(now, point)) || ! set.contains(hash(point, now))){
                            System.out.println("no");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("yes");
    }

    private static String hash(int x, int y){
        return x + "->" + y;
    }
}
