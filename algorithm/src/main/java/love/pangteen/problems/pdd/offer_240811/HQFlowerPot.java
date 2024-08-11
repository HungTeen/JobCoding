package love.pangteen.problems.pdd.offer_240811;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/11 22:17
 **/
public class HQFlowerPot {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int total = 0, ma = 0, mi = 0, ma_sum = 0, mi_sum = 0;
        for (int i=0; i<n; i++) {
            nums[i] = sc.nextInt() * 2 -1;
            total += nums[i];
            ma = Math.max(ma, Math.max(nums[i], ma_sum + nums[i]));
            ma_sum = Math.max(nums[i], ma_sum+nums[i]);
            mi = Math.min(mi, Math.min(nums[i], mi_sum + nums[i]));
            mi_sum = Math.min(nums[i], mi_sum+nums[i]);
        }

        Set<Integer> s = new HashSet<>();
        for (int i=mi; i<=ma; i++) {
            s.add(Math.abs(total + (-1) * i * 2));
        }
        System.out.println(s.size());
    }
}
