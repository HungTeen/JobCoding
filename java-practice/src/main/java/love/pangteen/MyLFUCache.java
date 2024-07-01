package love.pangteen;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/1 12:30
 **/
public class MyLFUCache<K, V> {

    private final Map<Integer, LinkedList<Node<K, V>>> bins;
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
        map.compute(key, (k, v) -> {
            if(v != null){
                removeFromBin(v);
                v.accessCount ++;
                addToBin(v);
            }
            return null;
        }
    }

    private void dropFromCache(){
        bins.computeIfPresent(this.minAccessCount, (k, v) -> {
            Node<K, V> first = v.removeFirst();
            map.remove(first.key);
            return v.isEmpty() ? null : v;
        });
    }

    /**
     * O(n)
     */
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
                LinkedList<Node<K, V>> list = new LinkedList<>();
                list.add(node);
                return list;
            } else {
                v.add(node);
                return v;
            }
        });
    }

    public static class Node<K, V> {

        private K key;
        private V value;
        private int accessCount;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.accessCount = 1;
        }

        public int getAccessCount() {
            return accessCount;
        }
    }
}
