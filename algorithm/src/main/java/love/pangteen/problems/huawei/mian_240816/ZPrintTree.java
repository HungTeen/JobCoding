package love.pangteen.problems.huawei.mian_240816;

import love.pangteen.Main;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/16 19:49
 **/
public class ZPrintTree {

    /**
     *      1
     *    2    3
     *  4  5  6  7
     * 8           9
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2, 4, 5);
        TreeNode node3 = new TreeNode(3, 6, 7);
        root.left = node2;
        root.right = node3;
        root.left.left.left = new TreeNode(8);
        root.right.right.right = new TreeNode(9);
        printTree(root);
    }

    private static void printTree(TreeNode root){
        boolean round = true; // true 表示从左到右。
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty()){
            // 取完上一层。
            int len = q.size();
            for(int i = 0; i < len; ++ i){
                TreeNode node = round ? q.pollFirst() : q.pollLast();
                System.out.print(node.val + " ");
                if (round) {
                    if (node.left != null) {
                        q.addLast(node.left);
                    }
                    if (node.right != null) {
                        q.addLast(node.right);
                    }
                } else {
                    if (node.right != null) {
                        q.addFirst(node.right);
                    }
                    if (node.left != null) {
                        q.addFirst(node.left);
                    }
                }
            }
            System.out.println();
            round = !round;
        }
    }

    public static class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode(int val){
            this.val = val;
        }

        public TreeNode(int val, int leftVal, int rightVal){
            this.val = val;
            this.left = new TreeNode(leftVal);
            this.right = new TreeNode(rightVal);
        }
    }
}
