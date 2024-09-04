package love.pangteen.problems.tencent.offer_240903;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/3 16:35
 **/
public class BigSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        int n = a.length(), m = b.length();
        int[] res = new int[n + m + 2];
        for(int i = n - 1; i >= 0; -- i){
            for(int j = m - 1; j >= 0; -- j){
                int pos = i + j + 3;
                int mult = res[pos] + (a.charAt(i) - '0') * (b.charAt(j) - '0');
                res[pos] = mult % 10;
                res[pos - 1] += mult / 10;
            }
        }
        // debug
        for(int i = 0; i < res.length; ++ i){
            System.out.print(res[i] + (i == res.length - 1 ? "\n" : " "));
        }
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        for(int i = 0; i < res.length; ++ i){
            if(res[i] != 0){
                start = true;
            }
            if(start){
                sb.append((char)(res[i] + '0'));
            }
        }
        if(!start){
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}
