package love.pangteen.problems.alibaba.internship_240515;

import org.w3c.dom.Node;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 16:20
 **/
public class BitSequence {

    private static final Node ROOT = new Node(false);

    /**
     * 字典树。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();
        for(int i = 0; i < n; ++ i){
            build(s, i);
        }
        System.out.println(dfs(ROOT, k));
    }

    private static void build(String s, int st){
        Node now = ROOT;
        for(int i = st; i < s.length(); ++ i){
            boolean zero = s.charAt(i) == '0';
            int pt = zero ? 0 : 1;
            if(now.nexts[pt] == null){
                now.nexts[pt] = new Node(zero);
            }
            now = now.nexts[pt];
            now.count ++;
        }
    }

    private static int dfs(Node now, int k){
        int ans = (now.count >= k ? 1 : 0);
        for(int i = 0; i < 2; ++ i){
            if(now.nexts[i] != null){
                ans += dfs(now.nexts[i], k);
            }
        }
        return ans;
    }

    private static class Node {

        public final boolean zero;
        public final Node[] nexts = new Node[2];
        public int count;

        private Node(boolean zero) {
            this.zero = zero;
        }
    }
}
