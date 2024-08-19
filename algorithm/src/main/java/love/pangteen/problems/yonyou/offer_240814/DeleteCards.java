package love.pangteen.problems.yonyou.offer_240814;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/14 21:00
 **/
public class DeleteCards {

    private static int[] bins = new int[5];
    private static int[] s;

    /**
     * 过了 87% 的用例。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int n = s1.length();
        s = new int[n];
        Arrays.setAll(s, i -> change(s1.charAt(i)));
        for (int i = 0; i < s2.length(); ++i) {
            bins[change(s2.charAt(i))]++;
        }
        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, dfs(0, n - 1, new int[5]));
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static int dfs(int l, int r, int[] use) {
        int ans = Integer.MAX_VALUE;
        if (l > r) {
            // System.out.println(l + "," + r + ":" + sum(use));
            return sum(use);
        }
        if (l == r) {
            int tmp = use[s[l]] + 2;
            if (tmp <= bins[s[l]]) {
                // System.out.println(l + "," + r + ":" + sum(use));
                return sum(use) + 2;
            }
            return ans;
        }
        if (r - l == 1) {
            if (s[l] == s[r]) {
                int tmp = use[s[l]] + 1;
                if (tmp <= bins[s[l]]) {
                    // System.out.println(l + "," + r + ":" + sum(use));
                    return sum(use) + 1;
                }
            } else {
                if (use[s[l]] + 2 <= bins[s[l]] && use[s[r]] + 2 <= bins[s[r]]) {
                    // System.out.println(l + "," + r + ":" + sum(use));
                    return sum(use) + 4;
                }
            }
            return ans;
        }
        if (s[l] == s[r]) {
            boolean flag = false;
            while (l + 1 < r && s[l] == s[l + 1]) {
                ++l;
                flag = true;
            }
            while (r - 1 > l && s[r] == s[r - 1]) {
                --r;
                flag = true;
            }
            int tmp = use[s[l]] + (flag ? 0 : 1);
            if (tmp <= bins[s[l]]) {
                int[] now = copy(use);
                now[s[l]] = tmp;
                ans = Math.min(ans, dfs(l + 1, r - 1, now));
            }
            if (!flag) {
                for (int i = l + 1; i < r; ++i) {
                    if (s[i] == s[l]) {
                        int j = i;
                        while (j + 1 < r && s[j + 1] == s[l]) ++j;
                        int sum = dfs(l + 1, i - 1, new int[5]) + dfs(j + 1, r - 1, new int[5]) + sum(use);
                        ans = Math.min(ans, sum);
                        i = j;
                    }
                }
            }
        }
        {
            int cnt = 1;
            int tl = l;
            while (tl + 1 < r && s[tl] == s[tl + 1]) {
                ++tl;
            }
            int tmpL = use[s[l]] + Math.max(0, 3 - (tl - l + 1));
            // System.out.println(l + "," + r + ": length : " + tmpL);
            if (tmpL <= bins[s[l]]) {
                ans = Math.min(ans, dfs(tl + 1, r, copy(use, s[l], tmpL)));
            }
            int tr = r;
            while (tr - 1 > l && s[tr] == s[tr - 1]) {
                --tr;
            }
            int tmpR = use[s[r]] + Math.max(0, 3 - (r - tr + 1));
            if (tmpR <= bins[s[r]]) {
                ans = Math.min(ans, dfs(l, tr - 1, copy(use, s[r], tmpR)));
            }
        }
        return ans;
    }

    private static int sum(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; ++i) ans += arr[i];
        return ans;
    }

    private static int[] copy(int[] arr) {
        return Arrays.copyOf(arr, 5);
    }

    private static int[] copy(int[] arr, int idx, int cnt) {
        int[] tmp = Arrays.copyOf(arr, 5);
        tmp[idx] = cnt;
        return tmp;
    }

    private static int change(char x) {
        return x == 'C' ? 0 : x == 'D' ? 1 : x == 'L' ? 2 : x == 'M' ? 3 : 4;
    }

}
