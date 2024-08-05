package love.pangteen.problems.yonyou.offer_240801;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 20:00
 **/
public class Pipeline {

    /**
     * 拓扑排序。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        List<Integer>[] edges = new List[n];
        int[] degree = new int[n];
        Arrays.setAll(edges, (i) -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; ++ i){
            String line = in.nextLine();
            if(line.equals("-1")) {
                q.addLast(i);
            } else {
                for(String now : line.split(" ")){
                    int val = Integer.parseInt(now);
                    edges[val].add(i);
                    ++ degree[i];
                }
            }

        }
        List<Integer> ans = new ArrayList<>();
        while(! q.isEmpty()){
            int now = q.pollFirst();
            ans.add(now);
            for(int nxt : edges[now]){
                if(-- degree[nxt] == 0){
                    q.add(nxt);
                }
            }
        }
        ans.sort(Integer::compareTo);
        for(int i = 0; i < ans.size(); ++ i){
            System.out.print(ans.get(i) + (i == ans.size() - 1 ? "\n" : " "));
        }
    }
}
