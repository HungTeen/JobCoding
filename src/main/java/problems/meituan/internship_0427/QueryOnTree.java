package main.java.problems.meituan.internship_0427;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 22:02
 **/
public class QueryOnTree {

    private static final int M = 20;

    /**
     * DFS遍历树 & 逆向并查集 & LCA。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int p = scanner.nextInt();
        List<int[]>[] edges = new List[n];
        int[][] edge = new int[n - 1][2];
        int[] dad = new int[n];
        Set<Integer> leftEdge = new HashSet<>();
        int[][] jump = new int[n][M]; // jump[i][j]表示i向上跳2^j步的节点编号。
        int[] depth = new int[n]; // depth[i]表示i节点的深度。
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
        dfs(0, 0, edges, jump, depth, xor, 0, 1);

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
                    int l = lca(jump, depth, u, v);
                    ans.add(xor[u] ^ xor[v]);
                }
            }
        }

        // 反向输出。
        for(int i = ans.size() - 1; i >= 0; -- i){
            System.out.println(ans.get(i));
        }
    }

    private static void dfs(int now, int fa, List<int[]>[] edges, int[][] jump, int[] depth, int[] xor, int x, int d){
        jump[now][0] = fa;
        xor[now] = x;
        depth[now] = d;
        for(int i = 1; i < jump[now].length; ++ i){
            jump[now][i] = jump[jump[now][i - 1]][i - 1];
        }
        for(int[] edge: edges[now]){
            if(edge[0] == fa) continue;
            dfs(edge[0], now, edges, jump, depth, xor, x ^ edge[1], d + 1);
        }
    }

    private static int lca(int[][] jump, int[] depth, int x, int y){
        // 保证x的深度大于等于y。
        if(depth[x] < depth[y]){
            int t = x;
            x = y;
            y = t;
        }
        // x使用二进制跳法跳到与y同一深度。
        for(int i = M - 1; i >= 0; -- i){
            if(depth[jump[x][i]] >= depth[y]){
                x = jump[x][i];
            }
        }
        if(x == y) return x;
        // x和y同时使用二进制跳法跳到最近公共祖先。
        for(int i = M - 1; i >= 0; -- i){
            if(jump[x][i] != jump[y][i]){
                x = jump[x][i];
                y = jump[y][i];
            }
        }
        return jump[x][0];
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
