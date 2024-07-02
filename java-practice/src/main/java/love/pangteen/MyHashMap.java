package love.pangteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @see HashMap
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/26 12:15
 **/
public class MyHashMap<K, V> {

    private Entry<K, V>[] entries;
    private int capacity;
    private int size = 0;
    private final float loadFactor;

    public MyHashMap(){
        this(16, 0.75F);
    }

    public MyHashMap(int capacity, float loadFactor){
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.entries = new Entry[capacity];
    }

    public void put(K key, V value){
        put(new Entry<>(key, value));
    }

    private void put(Entry<K, V> newEntry){
        checkSize();
        Entry<K, V> entry = getEntry(newEntry.key);
        if(entry != null){
            entry.value = newEntry.value;
        } else {
            int pos = getPos(newEntry.key);
            Entry<K, V> head = this.entries[pos];
            Entry<K, V> insert = newEntry;
            insert.next = null;
            if(head == null){
                this.entries[pos] = insert;
            } else {
                insert.next = head.next;
                head.next = insert;
            }
            ++ this.size;
        }
    }

    public V get(K key){
        Entry<K, V> entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    public void remove(K key){
        int pos = getPos(key);
        Entry<K, V> head = this.entries[pos];
        if(head != null){
            if(head.key.equals(key)){
                this.entries[pos] = head.next;
                -- this.size;
            } else {
                while(head.next != null && ! head.next.key.equals(key)){
                    head = head.next;
                }
                if(head.next != null){
                    head.next = head.next.next;
                    -- this.size;
                }
            }
        }
    }

    private Entry<K, V> getEntry(K key){
        int pos = getPos(key);
        Entry<K, V> entry = this.entries[pos];
        while(entry != null && ! entry.key.equals(key)){
            entry = entry.next;
        }
        return entry;
    }

    public List<Entry<K, V>> entries(){
        List<Entry<K, V>> ans = new ArrayList<>();
        for(int i = 0; i < capacity; ++ i){
            Entry<K, V> head = this.entries[i];
            while(head != null){
                ans.add(head);
                head = head.next;
            }
        }
        return ans;
    }

    public int getSize() {
        return size;
    }

    public void checkSize(){
        if(this.size * 1.0 / this.capacity >= this.loadFactor){
            this.resize();
        }
    }

    private void resize(){
        List<Entry<K, V>> ans = entries();
        this.capacity <<= 1;
        this.entries = new Entry[this.capacity];
        this.size = 0;
        ans.forEach(this::put);
    }

    private int getPos(K key){
        return key.hashCode() & (this.capacity - 1);
    }

    public static class Entry<K, V> {

        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K k, V v){
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
