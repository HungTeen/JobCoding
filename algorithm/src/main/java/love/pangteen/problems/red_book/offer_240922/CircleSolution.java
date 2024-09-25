package love.pangteen.problems.red_book.offer_240922;

import java.util.Arrays;
import java.util.Scanner;

public class CircleSolution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] f = new long[m * 2], pre = new long[m * 2 + 1];
        for(int i = 0; i < m; ++ i){
            f[i] = in.nextInt();
        }
        Arrays.sort(f, 0, m);
        // 构造环。
        for(int i = 0; i < m; ++ i){
            f[i + m] = f[i] + n;
        }
        // 前缀和。
        for(int i = 0; i < m * 2; ++ i){
            pre[i + 1] = pre[i] + f[i];
        }
        long minAns = Long.MAX_VALUE;
        int ansPos = -1;
        for(int i = 0; i < m; ++ i){
            int l = 0, r = 2 * m - 1;
            // 二分查找环一半可以直接减的位置，后一半可以反着减。
            int lPos = -1; // [i, lPos, rPos, i + m]
            while(l <= r){
                int mid = l + r >> 1;
                if(f[mid] <= f[i] + n / 2) {
                    l = mid + 1;
                    lPos = mid;
                } else r = mid - 1;
            }
            int rPos = lPos + 1;
            long leftSum = (pre[lPos + 1] - pre[i + 1]) - (long)(lPos - i) * f[i];
            long rightSum = (long)(i + m - rPos + 1) * f[i + m] - (pre[i + m + 1] - pre[rPos]);
            if(minAns > leftSum + rightSum){
                minAns = leftSum + rightSum;
                ansPos = i;
            }
        }
        System.out.println(f[ansPos]);
    }

}