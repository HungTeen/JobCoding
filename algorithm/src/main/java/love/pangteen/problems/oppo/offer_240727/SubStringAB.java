package love.pangteen.problems.oppo.offer_240727;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/30 16:45
 **/
public class SubStringAB {

    /**
     * 又臭又长的模拟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String a = in.next();
        String b = in.next();
        int[] binA = new int[26];
        int[] binB = new int[26];
        int[] bins = new int[26];
        for(int i = 0; i < s.length(); ++ i){
            bins[s.charAt(i) - 'a'] ++;
        }
        for(int i = 0; i < a.length(); ++ i){
            binA[Character.toLowerCase(a.charAt(i)) - 'a'] ++;
        }
        for(int i = 0; i < b.length(); ++ i){
            binB[Character.toLowerCase(b.charAt(i)) - 'a'] ++;
        }
        int ans = 0;
        for(int i = 0; i <= s.length(); ++ i){
            boolean match = true;
            for(int j = 0; j < 26; ++ j){
                if(binA[j] * i > bins[j]){
                    match = false;
                    break;
                }
            }
            if(! match) break;
            int cntB = Integer.MAX_VALUE;
            for(int j = 0; j < 26; ++ j){
                if(binB[j] > 0){
                    cntB = Math.min(cntB, (bins[j] - binA[j] * i) / binB[j]);
                }
            }
            ans = Math.max(ans, i + cntB);
        }
        System.out.println(ans);
    }

}
