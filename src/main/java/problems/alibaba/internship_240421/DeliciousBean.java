package main.java.problems.alibaba.internship_240421;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/5 17:19
 **/
public class DeliciousBean {

    /**
     * 哈希桶。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, Long> map = new HashMap<>();
        for(int i = 0; i < n; ++ i){
            int a = scanner.nextInt();
            int c = scanner.nextInt();
            map.merge(c, (long) a, Long::sum);
        }
        long ans = map.values().stream()
                .sorted((e1, e2) -> Long.compare(e2, e1))
                .limit(2)
                .reduce(0L, Long::sum);
        System.out.println(ans);
    }
}
