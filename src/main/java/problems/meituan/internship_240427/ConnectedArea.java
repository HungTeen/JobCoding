package main.java.problems.meituan.internship_240427;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 21:31
 **/
public class ConnectedArea {

    /**
     * 树的BFS遍历。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int mod = (int) (1e9 + 7);
        int n = scanner.nextInt();
        boolean[] red = new boolean[n];
        String str = scanner.next();
        List<Integer>[] edges = new List[n];
        for(int i = 0; i < n; ++ i) {
            red[i] = (str.charAt(i) == 'R');
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < n - 1; ++ i){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            edges[x - 1].add(y - 1);
            edges[y - 1].add(x - 1);
        }
        int ans = 0;
        for(int i = 0; i < n; ++ i){
            if(red[i]){
                int res = 1;
                Deque<Integer> q = new ArrayDeque<>();
                q.addLast(i);
                red[i] = false;
                while(!q.isEmpty()){
                    int u = q.pollFirst();
                    int now = u + 1;
                    for(int k = 2; k <= now; ++ k){
                        if(now % k == 0){
                            int cnt = 0;
                            while(now % k == 0){
                                ++ cnt;
                                now /= k;
                            }
                            res = (res * (cnt + 1)) % mod;
                        }
                    }
                    for(int v : edges[u]){
                        if(red[v]){
                            red[v] = false;
                            q.addLast(v);
                        }
                    }
                }
                ans = (ans + res) % mod;
            }
        }
        System.out.println(ans);
    }

}
