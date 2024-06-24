package love.pangteen.problems.red_book.internship_240528;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/24 12:06
 **/
public class ReadBook {

    /**
     * Set。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set<String> vis = new HashSet<>();
        for(int i = 0; i < n; ++ i){
            String x = scanner.next();
            if(! vis.contains(x)){
                vis.add(x);
                System.out.println(x);
            }
        }
    }
}
