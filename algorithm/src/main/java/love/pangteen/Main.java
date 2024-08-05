package love.pangteen;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        solution3();
    }

    static void solution3() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T > 0) {
            int n = sc.nextInt(), x = sc.nextInt() - 1;
            int[] ingrade = new int[n];
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
                g[a].add(b);
                g[b].add(a);
                ingrade[a]++;
                ingrade[b]++;
            }

            Deque<Integer> queue = new LinkedList<>();
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (ingrade[i] == 1) {
                    if (i == x) {
                        found = true;
                    }
                    queue.offer(i);
                }
            }

            // 逐层拓扑排序
            int l = queue.size(), total = l;
            if (!found) {
                while (l > 0) {
                    int now = queue.poll();
                    l--;
                    if (now == x) {
                        found = true;
                    }

                    for (int i = 0; i < g[now].size() && now != x; i++) {
                        int target = g[now].get(i);
                        ingrade[target]--;
                        if (ingrade[target] == 1) {
                            queue.offer(target);
                        }
                    }

                    if (l == 0) {
                        l = queue.size();
                        total += l;
                    }
                }
            }

            if (!found) {
                System.out.println("Draw");
            } else if (total % 2 != 0) {
                System.out.println("Xiaoyo");
            } else {
                System.out.println("Pyrmont");
            }

            T--;
        }
    }

}