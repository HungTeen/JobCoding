package love.pangteen.problems.red_book.internship_240528;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/24 12:34
 **/
public class ClickLike {

    /**
     * 二分答案【和240510的NumberOfLikes题目一样】
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] f = new int[n];
        long[] pre = new long[n];
        int max = -1, sec = -1;
        for(int i = 0; i < n; ++ i){
            f[i] = scanner.nextInt();
            pre[i] = f[i] + (i == 0 ? 0 : pre[i - 1]);
            if(f[i] >= max){
                sec = max;
                max = f[i];
            } else if(f[i] > sec){
                sec = f[i];
            }
        }
        for(int i = 0 ; i < n; ++ i){
            long l = 0, r = Integer.MAX_VALUE;
            long res = -1;
            while(l <= r){
                long mid = l + r >> 1;
                long nowHigh = f[i] + mid;
                long high = (f[i] == max ? sec : max);
                long sum = high * (n - 1);
                long curSum = pre[n - 1] - f[i];
                long fillHill = sum - curSum;
                long nextHigh = high;
                if(mid - 1 > fillHill){
                    long left = mid - 1 - fillHill;
                    long inc = (left + n - 2) / (n - 1);
                    nextHigh += inc;
                }
                if(nowHigh >= nextHigh){
                    res = mid;
                    r = mid - 1;
                } else l = mid + 1;
            }
            long ans = (res == -1 ? -1 : pre[n - 1] + (res == 0 ? 0 : res + res - 1));
            System.out.print(ans + (i == n - 1 ? "\n" : " "));
        }
    }
}
