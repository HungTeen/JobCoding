package love.pangteen.problems.alibaba.internship_240423;

import org.w3c.dom.Node;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 17:10
 **/
public class MaxOrder {

    /**
     * 贪心。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; ++ i){
            nodes[i] = new Node(scanner.nextInt(), 2);
        }
        for(int i = 0; i < n; ++ i){
            // 后两个放前面更优。
            if(i + 2 < n && nodes[i].val < nodes[i + 2].val && nodes[i + 1].val < nodes[i + 2].val){
                if(nodes[i].cnt >= 1 && nodes[i + 1].cnt >= 1 && nodes[i + 2].cnt >= 2){
                    nodes[i].cnt --;
                    nodes[i + 1].cnt --;
                    nodes[i + 2].cnt -= 2;
                    swap(nodes, i + 1, i + 2);
                    swap(nodes, i, i + 1);
                }
            }
            if(i + 1 < n && nodes[i].val < nodes[i + 1].val){
                if(nodes[i].cnt >= 1 && nodes[i + 1].cnt >= 1){
                    nodes[i].cnt --;
                    nodes[i + 1].cnt --;
                    swap(nodes, i, i + 1);
                }
            }
        }
        for(int i = 0; i < n; ++ i) {
            System.out.print(nodes[i].val + (i == n - 1 ? "\n" : " "));
        }
    }

    private static void swap(Node[] nodes, int i, int j){
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

    private static class Node {

        public int val;
        public int cnt;

        public Node(int val, int cnt){
            this.val = val;
            this.cnt = cnt;
        }

    }
}
