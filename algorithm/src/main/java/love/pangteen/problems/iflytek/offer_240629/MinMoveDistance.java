package love.pangteen.problems.iflytek.offer_240629;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/17 23:14
 **/
public class MinMoveDistance {

    /**
     * 没看懂题？
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] f = new int[n];
        long[] pre = new long[n + 1];
        for(int i = 0; i < n; ++ i){
            f[i] = scanner.nextInt();
            pre[i + 1] = pre[i] + f[i];
        }
        int mid = n >> 1;
        long ans = Integer.MAX_VALUE;
        if((n & 1) == 1){
            ans = Math.min(ans, get(f, pre, 0, mid - 1, n - 1));
            ans = Math.min(ans, get(f, pre, 0, mid, n - 1));
        } else {
            ans = get(f, pre, 0, mid - 1, n - 1);
        }
        System.out.println(ans);
    }

    /**
     * [l, mid] & [mid + 1, r]
     */
    private static long get(int[] f, long[] pre, int l, int mid, int r){
        long leftSum = pre[mid + 1] - pre[l];
        long rightSum = pre[r + 1] - pre[mid + 1];
        int leftCenter = (l + mid + 1) >> 1;
        int rightCenter = (mid + 1 + r) >> 1;
        long res = cal(mid - l + 1) + cal(r - mid);
        if(leftSum == rightSum){
            long ans = Integer.MAX_VALUE;
            int pos = mid;
            // Find left replace.
            while(pos >= l && f[pos] == 0){
                -- pos;
            }
            if(pos >= l && l != mid){
                ans = Math.min(ans, leave(res, l, mid, leftCenter, pos) + (rightCenter - pos));
            }
            pos = mid + 1;
            while(pos <= r && f[pos] == 0){
                ++ pos;
            }
            if(pos <= r && r != mid + 1){
                ans = Math.min(ans, leave(res, mid + 1, r, rightCenter, pos) + (pos - leftCenter));
            }
            return ans;
        }
        return res;
    }

    private static long leave(long res, int l, int r, int center, int pos){
        res = res - Math.abs(center - pos);
        if(center == pos && ((r - l + 1) & 1) == 0) -- res;
        return res;
    }

    private static long cal(int n){
        long res = 0;
        for(int i = 1; i <= n; ++ i){
            res += (i >> 1);
        }
        return res;
    }
}
