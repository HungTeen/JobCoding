package main.java.problems.meituan.internship_240427;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 22:02
 **/
public class QueryOnTree {

    private static final int M = 20;

    /**
     * DFS遍历树 & 逆向并查集。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int p = scanner.nextInt();
        List<int[]>[] edges = new List[n];
        int[][] edge = new int[n - 1][2];
        int[] dad = new int[n];
        Set<Integer> leftEdge = new HashSet<>();
        int[] xor = new int[n]; // xor[i]表示从i到根节点的异或值。

        // 初始化数据结构。
        for(int i = 0; i < n; ++ i){
            edges[i] = new ArrayList<>();
            dad[i] = i;
        }

        // 读取输入。
        for(int i = 0; i < n - 1; ++ i){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            edge[i][0] = u - 1;
            edge[i][1] = v - 1;
            edges[u - 1].add(new int[]{v - 1, w});
            edges[v - 1].add(new int[]{u - 1, w});
            leftEdge.add(i);
        }

        // DFS遍历树，初始化jump数组并获得xor值。
        dfs(0, 0, edges, xor, 0);

        // 离线处理。
        int[][] ops = new int[p][3];
        for(int i = 0; i < p; ++ i){
            ops[i][0] = scanner.nextInt();
            ops[i][1] = scanner.nextInt() - 1;
            if(ops[i][0] == 1){
                leftEdge.remove(ops[i][1]);
            } else {
                ops[i][2] = scanner.nextInt() - 1;
            }
        }

        // 构建初始并查集。
        leftEdge.forEach(i -> union(dad, edge[i][0], edge[i][1]));

        // 逆序构建并查集 & 查询答案。
        List<Integer> ans = new ArrayList<>();
        for(int i = p - 1; i >= 0; -- i){
            if(ops[i][0] == 1){
                int u = edge[ops[i][1]][0];
                int v = edge[ops[i][1]][1];
                union(dad, u, v);
            } else {
                int u = ops[i][1];
                int v = ops[i][2];
                if(seek(dad, u) != seek(dad, v)){
                    ans.add(-1);
                } else {
                    ans.add(xor[u] ^ xor[v]);
                }
            }
        }

        // 反向输出。
        for(int i = ans.size() - 1; i >= 0; -- i){
            System.out.println(ans.get(i));
        }
    }

    private static void dfs(int now, int fa, List<int[]>[] edges, int[] xor, int x){
        xor[now] = x;
        for(int[] edge: edges[now]){
            if(edge[0] == fa) continue;
            dfs(edge[0], now, edges, xor, x ^ edge[1]);
        }
    }

    private static void union(int[] dad, int x, int y){
        int fx = seek(dad, x);
        int fy = seek(dad, y);
        dad[fy] = fx;
    }

    private static int seek(int[] dad, int k){
        return dad[k] == k ? k : (dad[k] = seek(dad, dad[k]));
    }

}
