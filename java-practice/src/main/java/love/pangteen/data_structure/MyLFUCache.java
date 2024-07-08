package love.pangteen.data_structure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/1 12:30
 **/
public class MyLFUCache<K, V> {

    private final Map<Integer, MyLinkedList<K, V>> bins;
    private final Map<K, Node<K, V>> map;
    private final int capacity;
    private int len;
    private int minAccessCount;

    public MyLFUCache(int capacity) {
        this.bins = new ConcurrentHashMap<>();
        this.map = new ConcurrentHashMap<>();
        this.capacity = capacity;
    }

    public void put(K key, V value){
        map.compute(key, (k, v) -> {
            if(v == null){
                // 需要淘汰。
                if(this.len >= this.capacity){
                    this.dropFromCache();
                    this.len = this.capacity;
                } else {
                    ++ this.len;
                }
                Node<K, V> newNode = new Node<>(key, value);
                addToBin(newNode);
                this.minAccessCount = 1;
                return newNode;
            } else {
                v.value = value;
                return v;
            }
        });
    }

    /**
     * 线程不安全。
     */
    public V get(K key){
        Node<K, V> node = map.compute(key, (k, v) -> {
            if (v != null) {
                removeFromBin(v);
                v.accessCount++;
                addToBin(v);
                return v;
            }
            return null;
        });
        return node == null ? null : node.value;
    }

    private void dropFromCache(){
        bins.computeIfPresent(this.minAccessCount, (k, v) -> {
            Node<K, V> first = v.removeFirst();
            map.remove(first.key);
            return v.isEmpty() ? null : v;
        });
    }

    private void removeFromBin(Node<K, V> node){
        bins.computeIfPresent(node.accessCount, (k, v) -> {
            v.remove(node);
            if(v.isEmpty()){
                this.minAccessCount ++;
                return null;
            }
            return v;
        });
    }

    private void addToBin(Node<K, V> node){
        bins.compute(node.accessCount, (k, v) -> {
            if(v == null){
                MyLinkedList<K, V> list = new MyLinkedList<>();
                list.addLast(node);
                return list;
            } else {
                v.addLast(node);
                return v;
            }
        });
    }

    public static class MyLinkedList<K, V> {

        private final Node<K, V> head;
        private final Node<K, V> tail;

        public MyLinkedList() {
            this.head = new Node<>(null, null);
            this.tail = new Node<>(null, null);
            this.head.next = tail;
            this.tail.pre = head;
        }

        public void addLast(Node<K, V> node){
            this.tail.pre.next = node;
            node.pre = this.tail.pre;
            node.next = this.tail;
            this.tail.pre = node;
        }

        public Node<K, V> removeFirst(){
            Node<K, V> node = this.head.next;
            remove(node);
            return node;
        }

        public void remove(Node<K, V> node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = node.pre = null;
        }

        public boolean isEmpty(){
            return this.head.next == this.tail;
        }
    }

    public static class Node<K, V> {

        private final K key;
        private V value;
        private int accessCount;
        private Node<K, V> pre, next;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.accessCount = 1;
        }

    }
}
