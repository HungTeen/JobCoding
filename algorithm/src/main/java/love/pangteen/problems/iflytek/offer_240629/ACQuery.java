package love.pangteen.problems.iflytek.offer_240629;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/17 22:23
 **/
public class ACQuery {

    /**
     * 想过前缀和，但是没想过特判样例，改成了二分。
     */
    public static void main(String[] args) {
        preSum();
//        binarySearch();
    }

    private static void preSum(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s = scanner.next();
        int[] pre = new int[n + 1];
        for(int i = 2; i <= n; ++ i){
            if(s.charAt(i - 2) == 'A' && s.charAt(i - 1) == 'C'){
                pre[i] = pre[i - 2] + 1;
            } else pre[i] = pre[i - 1];
        }
        while(m -- > 0){
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int ans = pre[r] - pre[l - 1];
            if(l >= 2 && s.charAt(l - 1) == 'C' && s.charAt(l - 2) == 'A'){
                -- ans;
            }
            System.out.println(ans);
        }
    }

    private static void binarySearch(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s = scanner.next();
        List<Integer> pos = new ArrayList<>(); // 'A'的坐标
        for(int i = 1; i < n; ++ i){
            if(s.charAt(i) == 'C' && s.charAt(i - 1) == 'A'){
                pos.add(i);
            }
        }
        while(m -- > 0){
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int ll = 0, rr = pos.size() - 1;
            int lRes = -1;
            while (ll <= rr){
                int mid = ll + rr >> 1;
                if(pos.get(mid) >= l){
                    lRes = mid; rr = mid - 1;
                } else ll = mid + 1;
            }
            ll = 0;
            rr = pos.size() - 1;
            int rRes = -1;
            while(ll <= rr){
                int mid = ll + rr >> 1;
                if(pos.get(mid) + 1 <= r){
                    rRes = mid; ll = mid + 1;
                } else rr = mid - 1;
            }
            int ans = 0;
            if(lRes != -1 && rRes != -1 && rRes >= lRes){
                ans = rRes - lRes + 1;
            }
            System.out.println(ans);
        }
    }
}
