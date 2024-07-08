package love.pangteen.data_structure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/27 13:05
 **/
public class MyLRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head; // 越靠近头越不容易淘汰。
    private final Node<K, V> tail;

    public MyLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.r = this.tail;
        this.tail.l = this.head;
    }

    public void put(K key, V value){
        map.compute(key, (k, v) -> {
            Node<K, V> node = v;
            if(node == null){
                node = new Node<>(key, value);
            } else {
                removeFromList(node);
            }
            this.moveToHead(node);
            return node;
        });
        while(this.map.size() > this.capacity){
            Node<K, V> node = this.tail.l;
            removeFromList(node);
            map.remove(node.key);
        }
    }

    public V get(K key){
        Node<K, V> node = map.computeIfPresent(key, (k, v) -> {
            removeFromList(v);
            this.moveToHead(v);
            return v;
        });
        return node == null ? null : node.value;
    }

    private void removeFromList(Node<K, V> node){
        node.l.r = node.r;
        node.r.l = node.l;
    }

    private void moveToHead(Node<K, V> node){
        if(node != head.r){
            this.head.r.l = node;
            node.r = this.head.r;
            node.l = this.head;
            this.head.r = node;
        }
    }

    private static class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> l, r;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

    }
}
