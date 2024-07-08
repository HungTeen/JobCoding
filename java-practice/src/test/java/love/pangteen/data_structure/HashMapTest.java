package love.pangteen.data_structure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/2 12:58
 **/
public class HashMapTest {

    @Test
    public void test(){
        MyHashMap<String, Integer> map = new MyHashMap<>(2, 1F);
        map.put("1", 1);
        Assert.assertEquals(map.get("1"), Integer.valueOf(1));
        map.put("2", 2);
        Assert.assertEquals(map.get("2"), Integer.valueOf(2));
        map.put("3", 3);
        Assert.assertEquals(map.get("3"), Integer.valueOf(3));
        map.remove("2");
        Assert.assertNull(map.get("2"));
        for(int i = 10; i < 15; ++ i){
            map.put("Test" + i, i);
        }
    }

}
