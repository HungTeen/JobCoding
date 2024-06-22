package love.pangteen.problems.ctrip.internship_240520;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 18:14
 **/
public class OddNumber {

    /**
     * 模拟。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int p = scanner.nextInt();
        int m = 0;
        for(int i = 0; i < s.length(); ++ i){
            int tmp = (s.charAt(i) - '0');
            if((tmp & 1) == 1){
                m = m * 10 + tmp;
            }
        }
        System.out.println(m % p);
    }
}
