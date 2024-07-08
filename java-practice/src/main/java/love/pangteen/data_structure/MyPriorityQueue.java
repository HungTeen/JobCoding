package love.pangteen.data_structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see PriorityQueue
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/25 12:12
 **/
public class MyPriorityQueue<T> {

    private Object[] queue;
    private int capacity;
    private int len;
    private final Comparator<? super T> comparator;

    public MyPriorityQueue(Comparator<? super T> comparator){
        this(11, comparator);
    }

    public MyPriorityQueue(int capacity, Comparator<? super T> comparator){
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.comparator = comparator;
    }

    public void add(T element){
        if(this.len >= this.capacity){
            this.resize();
        }
        this.queue[this.len ++] = element;
        this.siftUp(this.len - 1);
    }

    public T poll(){
        if(this.len == 0) throw new RuntimeException("Empty");
        Object res = this.queue[0];
        this.queue[0] = this.queue[-- this.len];
        this.siftDown(0);
        return (T) res;
    }

    public int size(){
        return this.len;
    }

    private void siftUp(int pos){
        while(pos > 0){
            int parent = (pos - 1) >> 1;
            if(this.compare(pos, parent)){
                swap(pos, parent);
                pos = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int pos){
        while(pos < this.len){
            int l = 2 * (pos + 1) - 1;
            int r = 2 * (pos + 1);
            int lc = (l < this.len && compare(l, pos)) ? l : -1;
            int rc = (r < this.len && compare(r, pos)) ? r : -1;
            if(lc != -1 && rc != -1){
                int res = (compare(lc, rc) ? lc : rc);
                swap(pos, res);
                pos = res;
            } else if(lc != -1){
                swap(pos, lc);
                pos = lc;
            } else if(rc != -1){
                swap(pos, rc);
                pos = rc;
            } else break;
        }
    }

    private void resize(){
        this.capacity <<= 1;
        this.queue = Arrays.copyOf(this.queue, this.capacity);
    }

    /**
     * x < y返回true。
     */
    private boolean compare(int x, int y){
        return comparator.compare((T) queue[x], (T) queue[y]) < 0;
    }

    private void swap(int x, int y){
        Object tmp = queue[x];
        queue[x] = queue[y];
        queue[y] = tmp;
    }

}
