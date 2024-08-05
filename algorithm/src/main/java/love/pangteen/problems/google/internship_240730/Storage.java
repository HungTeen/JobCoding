package love.pangteen.problems.google.internship_240730;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 19:32
 **/
public class Storage {

    /**
     * TreeSet.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeSet<Integer> empty = new TreeSet<>();
        Map<String, Integer> map = new HashMap<>();
        int ans = -1;
        int nextId = 1;
        for(int i = 0; i < n; ++ i){
            String now = in.next();
            if(map.containsKey(now)){
                empty.add(map.get(now));
                map.remove(now);
            } else {
                if(! empty.isEmpty()){
                    Integer key = empty.pollFirst();
                    ans = key;
                    map.put(now, key);
                } else {
                    ans = nextId;
                    map.put(now, nextId ++);
                }
            }
        }
        System.out.println(ans);
    }

    /*
    6
    A B C B D A

    3
    A B C

    4
    A A B A
     */
}
