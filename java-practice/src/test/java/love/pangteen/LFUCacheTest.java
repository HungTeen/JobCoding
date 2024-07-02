package love.pangteen;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/2 12:52
 **/
public class LFUCacheTest {

    @Test
    public void test(){
        MyLFUCache<String, Integer> cache = new MyLFUCache<>(2);
        cache.put("1", 1);
        Assert.assertEquals(cache.get("1"), (Integer) 1);
        cache.put("2", 2);
        Assert.assertEquals(cache.get("2"), (Integer) 2);
        cache.put("3", 3);
        Assert.assertEquals(cache.get("3"), (Integer) 3);
        Assert.assertNull(cache.get("1"));
        cache.get("2");
        cache.put("4", 4);
        Assert.assertEquals(cache.get("4"), (Integer) 4);
        Assert.assertNull(cache.get("3"));
    }
}
