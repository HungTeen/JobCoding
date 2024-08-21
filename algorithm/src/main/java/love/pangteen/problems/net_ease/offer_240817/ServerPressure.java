package love.pangteen.problems.net_ease.offer_240817;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/21 21:28
 **/
public class ServerPressure {

    /**
     * 差分。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t -- > 0){
            int n = in.nextInt();
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int i = 0; i < n; ++ i){
                String time = in.next();
                int people = in.nextInt();
                int len = in.nextInt();
                int startTime = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
                map.merge(startTime, people, Integer::sum);
                map.merge(startTime + len, -people, Integer::sum);
            }
            int now = 0;
            int ans = 0;
            for(var e : map.entrySet()){
                now += e.getValue();
                ans = Math.max(ans, now);
            }
            System.out.println(ans);
        }
    }
}
