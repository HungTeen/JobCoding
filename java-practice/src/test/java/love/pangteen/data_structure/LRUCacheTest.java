package love.pangteen.data_structure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/27 12:57
 **/
public class LRUCacheTest {

    @Test
    public void test(){
        MyLRUCache<String, Integer> cache = new MyLRUCache<>(3);
        cache.put("1", 1);
        cache.put("2", 2);
        cache.put("3", 3);
        Assert.assertEquals((int) cache.get("1"), 1);
        Assert.assertEquals((int) cache.get("2"), 2);
        Assert.assertEquals((int) cache.get("3"), 3);
        cache.put("4", 4);
        Assert.assertNull(cache.get("1"));
        Assert.assertEquals((int) cache.get("4"), 4);
        cache.get("2");
        cache.put("5", 5);
        Assert.assertNull(cache.get("3"));
    }
}
