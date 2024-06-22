package love.pangteen.problems.alibaba.cloud_240421;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 18:53
 **/
public class SameCharSequence {

    /**
     * 双指针。
     * 样例：
     * 9 2
     * aabccddde
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();
        int l = 0, r = 0, cnt = 0;
        int ans = n + 1;
        while(true){
            while(cnt < k && r < n){
                if(r - 1 >= l && s.charAt(r) == s.charAt(r - 1)){
                    cnt++;
                }
                ++ r;
            }
            if(cnt < k) break;
            ans = Math.min(ans, r - l);
            if(l + 1 < r && s.charAt(l) == s.charAt(l + 1)){
                -- cnt;
            }
            ++ l;
        }
        System.out.println(ans == n + 1 ? -1 : ans);
    }
}
