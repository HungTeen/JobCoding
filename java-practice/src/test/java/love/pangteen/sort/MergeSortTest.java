package love.pangteen.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/1 22:41
 **/
public class MergeSortTest {

    @Test
    public void testMergeSort() {
        for(int i = 0; i < 100; ++ i){
            Random random = new Random(System.currentTimeMillis());
            int n = random.nextInt(100) + 10;
            int[] f = new int[n];
            int[] g = new int[n];
            Arrays.setAll(f, idx -> random.nextInt(1000));
            Arrays.setAll(g, idx -> f[idx]);
            Arrays.sort(g);
            MergeSort.mergeSort(f);
            for(int j = 0; j < n; ++ j){
                Assert.assertEquals(f[j], g[j]);
            }
        }
    }
}
