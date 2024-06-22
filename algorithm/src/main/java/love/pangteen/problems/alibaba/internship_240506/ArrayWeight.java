package love.pangteen.problems.alibaba.internship_240506;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 18:51
 **/
public class ArrayWeight {

    /**
     * 用TreeMap维护最小最大值，其实麻烦了。维护个次小值就行了。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        long sum = 0;
        TreeMap<Integer, Integer> bins = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            sum += a[i];
            bins.put(a[i], bins.getOrDefault(a[i], 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int next = a[i] << 1;
            bins.computeIfPresent(a[i], (k, v) -> v - 1 == 0 ? null : v - 1);
            bins.put(next, bins.getOrDefault(next, 0) + 1);
            long res = (sum + a[i]) * (bins.lastKey() - bins.firstKey());
            System.out.print(res + (i == n - 1 ? "\n" : " "));
            bins.computeIfPresent(next, (k, v) -> v - 1 == 0 ? null : v - 1);
            bins.put(a[i], bins.getOrDefault(a[i], 0) + 1);
        }
    }
}
