package love.pangteen.problems.iflytek.offer_240713;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/13 22:39
 **/
public class Find01 {

    /**
     * 记录最近位置。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-- > 0){
            int n = scanner.nextInt();
            String s = scanner.next();
            int lastZero = -1;
            int lastOne = -1;
            for(int i = 0; i < n; ++ i){
                int ans = -1;
                if(s.charAt(i) == '0'){
                    if(lastOne != -1) ans = lastOne + 1;
                    lastZero = i;
                } else {
                    if(lastZero != -1) ans = lastZero + 1;
                    lastOne = i;
                }
                System.out.print(ans + (i == n - 1 ? "\n" : " "));
            }
        }
    }
}
