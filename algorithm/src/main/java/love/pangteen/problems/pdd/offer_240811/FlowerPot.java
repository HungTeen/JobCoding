package love.pangteen.problems.pdd.offer_240811;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/11 22:20
 **/
public class FlowerPot {

    /**
     * 枚举所有区间的改变，暴力骗分 60% <br>
     * 考虑每次只能加一和减一，其实只要找到变化区间的最大值和最小值，中间必定连续。<br>
     * 用类似连续区间最大和的方法，找到最大值和最小值，然后枚举中间的值即可。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int total = 0;
        for (int i=0; i<n; i++) {
            nums[i] = sc.nextInt() * 2 -1;
            total += nums[i];
        }

        Set<Integer> s = new HashSet<>();
        int max = 0;
        int sum = 0;
        for(int i = 0; i < n; ++ i){
            sum += nums[i];
            if(sum < 0) {
                sum = 0;
            } else {
                max = Math.max(max, sum);
            }
        }
        sum = 0;
        int min = 0;
        for(int i = 0; i < n; ++ i){
            sum += nums[i];
            if(sum > 0) {
                sum = 0;
            } else {
                min = Math.min(min, sum);
            }
        }
        //System.out.println(min + "," + max);
        for(int i = min; i <= max; ++ i){
            s.add(Math.abs(total - 2 * i));
        }
        System.out.println(s.size());
    }
}
