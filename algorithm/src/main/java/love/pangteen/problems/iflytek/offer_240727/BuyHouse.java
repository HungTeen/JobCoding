package love.pangteen.problems.iflytek.offer_240727;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/29 22:48
 **/
public class BuyHouse {

    /**
     * 贪心 + TreeMap。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        TreeMap<Integer, Integer> people = new TreeMap<>();
        for(int i = 0; i < n; ++ i){
            people.merge(in.nextInt(), 1, Integer::sum);
        }
        List<int[]> houses = new ArrayList<>();
        for(int i = 0; i < m; ++ i){
            int x = in.nextInt();
            int y = in.nextInt();
            houses.add(new int[]{x, y});
        }
        houses.sort((a, b) -> Integer.compare(b[0], a[0]));
        long ans = 0;
        for(int i = 0; i < m; ++ i){
            var now = houses.get(i);
            Integer reduce = people.ceilingKey(now[1]);
            if(reduce == null){
                continue;
            } else {
                ans += now[0];
                people.compute(reduce, (k, v) -> {
                    return v == 1 ? null : v - 1;
                });
            }
        }
        System.out.println(ans);
    }
}
