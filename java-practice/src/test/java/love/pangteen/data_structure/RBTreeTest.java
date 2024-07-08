package love.pangteen.data_structure;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/8 15:17
 **/
public class RBTreeTest {

    /**
     *       3
     *      / \
     *     1   5
     *      \   \
     *       2   6
     *            \
     *             7
     */
    @Test
    public void test(){
        MyRBTree<Integer> tree = new MyRBTree<>(Comparator.naturalOrder());
        tree.add(5);
        Assert.assertTrue(tree.contains(5));
        tree.add(3);
        Assert.assertTrue(tree.contains(3));
        tree.add(1);
        Assert.assertTrue(tree.contains(1));
        Assert.assertEquals(tree.root().value().intValue(), 3);
        tree.add(1);
        Assert.assertEquals(tree.size(), 3);
        tree.add(2);
        Assert.assertTrue(tree.contains(2));
        Assert.assertEquals(tree.root().value().intValue(), 3);
        tree.add(6);
        Assert.assertTrue(tree.contains(6));
        tree.add(7);
        Assert.assertTrue(tree.contains(7));
    }
}
