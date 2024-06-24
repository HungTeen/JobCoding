package love.pangteen.problems.red_book.internship_240510;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/27 13:05
 **/
public class NumberOfLikes {

    /**
     * 找规律。【和240528的ClickLike题目一样】
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] h = new int[n];
        long sum = 0;
        long maxH = 0;
        for(int i = 0; i < n; ++ i){
            h[i] = scanner.nextInt();
            sum += h[i];
            maxH = Math.max(maxH, h[i]);
        }
        for(int i = 0; i < n; ++ i){
            long ans = sum;
            long maxAdd = maxH * (n - 1) - (sum - h[i]);
            if(h[i] == maxH){
            } else if(maxAdd + 1 >= maxH){
                ans += 2 * (maxH - h[i]) - 1;
            } else if(n > 2){
                ans += 2 * maxAdd;
                long now = h[i] + maxAdd;
                long dif = maxH - now;
                long cycle = (dif - 1) / (n - 2);
                long left = (dif - 1) % (n - 2);
                ans += cycle * 2 * (n - 1);
                if(left > 0)
                    ans += 2 * (left + 1);
                ++ ans;
            } else {
                ans = -1;
            }
            System.out.print(ans + (i == n - 1 ? "\n" : " "));
        }
    }
}
