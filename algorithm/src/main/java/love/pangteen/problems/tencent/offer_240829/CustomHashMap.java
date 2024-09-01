package love.pangteen.problems.tencent.offer_240829;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/29 14:42
 **/
public class CustomHashMap {

    private final int size;
    private final Node[] nodes;

    public CustomHashMap(int size){
        this.size = size;
        this.nodes = new Node[size];
    }

    public void put(int key, int value){
        Node old = findNode(key);
        if(old == null){
            Node newNode = new Node(key, value);
            int pos = pos(key);
            Node root = nodes[pos];
            // 头插法。
            if(root == null){
                nodes[pos] = newNode;
            } else {
                newNode.next = root.next;
                root.next = newNode;
            }
        } else {
            old.value = value;
        }
    }

    /**
     * 不存在返回null。
     */
    public Integer get(int key){
        Node old = findNode(key);
        return old == null ? null : old.value;
    }

    public void delete(int key){
        int pos = pos(key);
        Node tmp = nodes[pos];
        if(tmp == null){
            throw new RuntimeException("不存在key");
        } else if(tmp.key == key){
            nodes[pos] = tmp.next;
            tmp.next = null;
        } else {
            while(tmp.next != null && tmp.next.key != key){
                tmp = tmp.next;
            }
            if(tmp.next != null){
                tmp.next = tmp.next.next;
            }
        }
    }

    /**
     * 找到key对应的node，找不到返回null。
     */
    private Node findNode(int key){
        int pos = pos(key);
        Node tmp = nodes[pos];
        while(tmp != null && tmp.key != key){
            tmp = tmp.next;
        }
        return tmp;
    }

    private int pos(int key){
        return key % size;
    }

    private static class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap(10);
        map.put(1, 1);
        System.out.println("Key 1 : " + map.get(1));
        map.put(2, 2);
        System.out.println("Key 2 : " + map.get(2));
        map.put(1, 2);
        System.out.println("Key 1 : " + map.get(1));
        map.put(11, 11);
        System.out.println("Key 11 : " + map.get(11));
        map.put(21, 21);
        System.out.println("Key 21 : " + map.get(21));
        map.delete(21);
        System.out.println("Key 21 : " + map.get(21));
        System.out.println("Key 11 : " + map.get(11));
        System.out.println("Key 1 : " + map.get(1));
        map.delete(1);
        System.out.println("Key 11 : " + map.get(11));
        System.out.println("Key 1 : " + map.get(1));
    }

}
