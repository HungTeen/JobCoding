package love.pangteen.core;

/**
 * Definition for singly-linked list.
 *
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/4 11:52
 **/

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void printList(ListNode head){
        int cnt = 0;
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
            if(++ cnt >= 1000){
                System.out.println("\n死循环了！");
                return;
            }
        }
        System.out.println();
    }
}