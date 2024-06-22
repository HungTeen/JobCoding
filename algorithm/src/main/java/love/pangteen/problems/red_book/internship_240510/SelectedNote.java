package love.pangteen.problems.red_book.internship_240510;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/27 12:45
 **/
public class SelectedNote {

    /**
     * 优先队列。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            idx[i] = i;
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        Arrays.sort(idx, Comparator.comparingInt(i -> a[i]));
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        List<Integer> list = Arrays.stream(a).boxed().sorted().distinct().toList();
        long ans = 0;
        long sum = 0;
        int r = n - 1;
        for (int least : list) {
            while (!q.isEmpty() && q.peek()[0] < least) {
                sum -= q.poll()[1];
            }
            while (r >= 0 && q.size() < k) {
                q.add(new int[]{b[idx[r]], a[idx[r]]});
                sum += a[idx[r]];
                --r;
            }
            if (q.size() < k) break;
            ans = Math.max(ans, sum * least);
        }
        System.out.println(ans);
    }
}
