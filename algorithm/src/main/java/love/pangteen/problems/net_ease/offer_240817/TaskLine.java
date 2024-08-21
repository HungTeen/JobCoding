package love.pangteen.problems.net_ease.offer_240817;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/21 22:31
 **/
public class TaskLine {

    /**
     * 大模拟真恶心。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        TreeMap<Integer, TaskNode> series = new TreeMap<>();
        Map<Integer, TaskNode> taskById = new HashMap<>();
        for(int i = 0; i < n; ++ i){
            TaskNode head = new TaskNode(-1);
            int x = in.nextInt();
            int y = in.nextInt();
            series.put(x, head);
            for(int j = 0; j < y; ++ j){
                int t = in.nextInt();
                TaskNode tmp = new TaskNode(t);
                taskById.put(t, tmp);
                head.next = tmp;
                tmp.prev = head;
                head = tmp;
            }
            TaskNode tail = new TaskNode(-1);
            head.next = tail;
            tail.prev = head;
        }
        for(int i = 0; i < m; ++ i){
            int x = in.nextInt();
            taskById.get(x).next.chose = true;
        }
        for (var e : series.entrySet()) {
            TaskNode head = e.getValue();
            boolean findChose = false;
            while(head != null){
                if(head.chose) {
                    findChose = true;
                    break;
                }
                head = head.next;
            }
            if(! findChose){
                e.getValue().next.chose = true;
            }
        }
        for(int i = 0; i < k; ++ i){
            int op = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            int newT = in.nextInt();
            if(!series.containsKey(s)) continue;
            if(op == 1){
                TaskNode pre = (t == 0 ? series.get(s) : taskById.get(t));
                TaskNode node = new TaskNode(newT);
                node.next = pre.next;
                node.prev = pre;
                pre.next.prev = node;
                pre.next = node;
                if(node.next.id == -1 && node.next.chose){
                    node.chose = true;
                }
            } else {
                TaskNode now = taskById.get(t);
                if(now.chose){
                    now.next.chose = true;
                }
                now.prev.next = now.next;
                now.next.prev = now.prev;
                now.prev = null;
                now.next = null;
                taskById.remove(t);
            }
        }
        for (var e : series.entrySet()) {
            TaskNode head = e.getValue();
            boolean notFinish = false;
            while(head != null){
                if(head.chose) notFinish = true;
                if(notFinish && head.id != -1){
                    System.out.print(head.id + " ");
                }
                head = head.next;
            }
            System.out.println();
        }
    }

    private static class TaskNode {

        private TaskNode next;
        private TaskNode prev;
        private final int id;
        private boolean chose = false;

        private TaskNode(int id) {
            this.id = id;
        }
    }
}
