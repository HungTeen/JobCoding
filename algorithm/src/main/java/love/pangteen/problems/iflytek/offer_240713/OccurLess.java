package love.pangteen.problems.iflytek.offer_240713;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/13 22:45
 **/
public class OccurLess {

    /**
     * TreeMap。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        TreeMap<Integer, Integer> bin = new TreeMap<>();
        for(int i = 0; i < n; ++ i){
            int x = scanner.nextInt();
            bin.merge(x, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : bin.entrySet()) {
            if(entry.getValue() <= m) {
                System.out.print(entry.getKey());
                return;
            }
        }
    }
}
