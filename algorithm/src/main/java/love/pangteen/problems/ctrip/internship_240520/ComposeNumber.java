package love.pangteen.problems.ctrip.internship_240520;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/22 18:19
 **/
public class ComposeNumber {

    private static int ans = -1;

    /**
     * DFS枚举。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        dfs(s, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(String s, int flag, int now){
        if(ans != -1) return;
        if(flag == (1 << s.length()) - 1){
//            System.out.println(now);
            for(int j = 2; j <= Math.sqrt(now); ++ j){
                if(now % j == 0) return;
            }
            ans = now;
            return;
        }
        for(int i = 0; i < s.length(); ++ i){
            if(((flag >> i) & 1) == 0){
                if(flag == 0 && s.charAt(i) == '0') continue;
                dfs(s, flag | (1 << i), now * 10 + s.charAt(i) - '0');
            }
        }
    }
}
