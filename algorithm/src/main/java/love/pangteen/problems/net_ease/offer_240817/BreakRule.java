package love.pangteen.problems.net_ease.offer_240817;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/21 21:42
 **/
public class BreakRule {

    /**
     * DFS。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, Integer> dad = new HashMap<>();
        Map<Integer, Integer> height = new HashMap<>();
        for(int i = 0; i < n / 2; ++ i){
            int x = in.nextInt(), y = in.nextInt();
            dad.put(y, x);
        }
        List<Integer> ans = new ArrayList<>();
        int m = in.nextInt();
        for(int i = 0; i < m / 2; ++ i){
            int x = in.nextInt(), y = in.nextInt();
            int fx = findHeight(height, dad, x);
            int fy = findHeight(height, dad, y);
            if(Math.abs(fx - fy) < 3){
                ans.add(x);
                ans.add(y);
            }
        }
        ans.sort(Integer::compareTo);
        for(int i = 0; i < ans.size(); ++ i){
            System.out.print(ans.get(i) + (i == ans.size() - 1 ? "\n" : " "));
        }
    }

    private static int findHeight(Map<Integer, Integer> height, Map<Integer, Integer> dad, int x){
        if(height.containsKey(x)) return height.get(x);
        if(dad.containsKey(x)){
            int h = findHeight(height, dad, dad.get(x)) + 1;
            height.put(x, h);
            return h;
        }
        return 0;
    }
}
