package love.pangteen.problems.leetcode;

import love.pangteen.core.ListNode;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/4 11:53
 **/
public class ReverseKGroup {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        ListNode head = new ListNode(1);
        ListNode pre = head;
        for(int i = 2; i <= n; ++ i){
            ListNode now = new ListNode(i);
            pre.next = now;
            pre = now;
        }
        ListNode.printList(head);
        Solution solution = new Solution();
        ListNode ans = solution.reverseKGroup(head, k);
        ListNode.printList(ans);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     *  }
     *
     **/
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            int cnt = 0;
            ListNode root = new ListNode(0, head);
            ListNode pre = root;
            ListNode tmp = head;
            while(tmp != null){
                if(++ cnt == k){
                    ListNode nxt = tmp.next;
                    ListNode firstNode = pre.next;
                    reverse(pre, tmp);
                    pre.next = tmp;
                    pre = firstNode;
                    pre.next = nxt;
                    tmp = nxt;
                    cnt = 0;
//                    break;
                    continue;
                }
                tmp = tmp.next;
            }
//            if(cnt != 0){
//                reverse(pre, tmp);
//            }
            return root.next;
        }

        /**
         * pre tmp nxt
         * 翻转 now.next 到 last 之间的节点。
         */
        private void reverse(ListNode pre, ListNode last){
            ListNode tmp = pre.next;
            while(tmp != null){
                ListNode nxt = tmp.next;
                tmp.next = pre;
                pre = tmp;
                if(tmp == last) {
                    break;
                }
                tmp = nxt;
            }
        }

    }
}
