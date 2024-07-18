package love.pangteen.problems.huawei.offer_240717;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/18 22:32
 **/
public class FileSystem {

    /**
     * 没看清输入规则，根本不用建树。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        String[] parents = line1.split(" ");
        String[] children = line2.split(" ");
        int n = parents.length;
        String query = scanner.next();
        Set<String> set = new HashSet<>();
        set.add(query);
        System.out.print(query);
        for(int i = 0; i < n; ++ i){
            if(set.contains(parents[i])){
                System.out.print(" " + children[i]);
                set.add(children[i]);
            }
        }
        System.out.println();
    }

}
