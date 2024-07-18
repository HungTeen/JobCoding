package love.pangteen.problems.huawei.offer_240717;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/18 22:50
 **/
public class SoftwareDependency {

    /**
     * DFS。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        List<Integer>[] edges = new List[n + 1];
        for(int i = 0; i <= n; ++ i){
            edges[i] = new ArrayList<>();
        }
        while(d -- > 0){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            edges[x].add(y);
        }
        int q = scanner.nextInt();
        System.out.println(q);
        while(q -- > 0){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println((isDependency(edges, x, y) ? 1 : 0));
        }
    }

    private static boolean isDependency(List<Integer>[] edges, int x, int target){
        boolean reach = false;
        for(int y : edges[x]){
            if(y == target) reach = true;
            else {
                reach |= isDependency(edges, y, target);
            }
        }
        return reach;
    }
}
