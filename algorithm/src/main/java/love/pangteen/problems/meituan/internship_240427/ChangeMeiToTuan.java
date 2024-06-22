package love.pangteen.problems.meituan.internship_240427;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 20:51
 **/
public class ChangeMeiToTuan {

    /**
     * 简单模拟即可。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        for(int i = 0; i < s.length(); ++ i){
            if(i + 2 < s.length()) {
                if (s.charAt(i) == 'm' && s.charAt(i + 1) == 'e' && s.charAt(i + 2) == 'i') {
                    System.out.print("tuan");
                    i += 2;
                    continue;
                }
            }
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
}
