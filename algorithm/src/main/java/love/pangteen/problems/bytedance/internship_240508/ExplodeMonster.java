package love.pangteen.problems.bytedance.internship_240508;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 16:46
 **/
public class ExplodeMonster {

    /**
     * 排序。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] hp = new int[n];
        for(int i = 0 ; i < n; ++ i){
            hp[i] = scanner.nextInt();
        }
        Arrays.sort(hp);
        int sum = 0;
        for(int i = 0; i < n; ++ i){
            sum += hp[i];
            if(sum >= hp[n - 1]){
                System.out.println(hp[i]);
                break;
            }
        }
    }
}
