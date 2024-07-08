package love.pangteen.data_structure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/27 12:49
 **/
public class MyEasyLRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int cacheSize;

    public MyEasyLRUCache(int cacheSize){
        super(16, 0.75F, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

}
