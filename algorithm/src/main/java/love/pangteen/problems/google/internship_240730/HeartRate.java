package love.pangteen.problems.google.internship_240730;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 19:43
 **/
public class HeartRate {

    /**
     * 双指针。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        String[] a = new String[n];
        for(int i = 0; i < n; ++ i) f[i] = in.nextInt();
        for(int i = 0; i < n; ++ i) a[i] = in.next();
        int ans = 0;
        int l = 0;
        while(l < n - 1){
            int min = f[l], max = f[l];
            int r = l + 1;
            while(r < n && a[l].equals(a[r])){
                min = Math.min(min, f[r]);
                max = Math.max(max, f[r]);
                ++ r;
            }
            ans = Math.max(ans, max - min);
            l = r;
        }
        System.out.println(ans);
    }

    /*
    4
    60 100 90 80
    A B B B

    5
    100 87 90 90 125
    A A A B C
     */
}
