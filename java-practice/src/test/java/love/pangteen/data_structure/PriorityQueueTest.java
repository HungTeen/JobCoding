package love.pangteen.data_structure;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/2 12:55
 **/
public class PriorityQueueTest {

    @Test
    public void test(){
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>(1, Comparator.naturalOrder());
        q.add(1);
        q.add(30);
        q.add(8);
        q.add(99);
        Assert.assertEquals(q.poll(), (Integer) 1);
        Assert.assertEquals(q.poll(), (Integer) 8);
        q.add(-1);
        Assert.assertEquals(q.size(),  3);
        Assert.assertEquals(q.poll(), Integer.valueOf(-1));
        Assert.assertEquals(q.poll(), Integer.valueOf(30));
        Assert.assertEquals(q.poll(), Integer.valueOf(99));
    }
}
