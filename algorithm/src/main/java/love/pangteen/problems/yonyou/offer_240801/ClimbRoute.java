package love.pangteen.problems.yonyou.offer_240801;

import java.util.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/5 21:06
 **/
public class ClimbRoute {

    /**
     * 枚举所有可能情况，复杂度O（2^n * n * logn)
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] score = new int[n];
        int[] height = new int[n];
        int[] canRelax = new int[n];
        ++ k; // 理解错了k的意思。
        Arrays.setAll(score, i -> in.nextInt());
        Arrays.setAll(height, i -> in.nextInt());
        Arrays.setAll(canRelax, i -> in.nextInt());
        int maxScore = 0;
        List<Integer> ansPoints = new ArrayList<>();
        List<Integer> ansRelax = new ArrayList<>();
        // 枚举所有访问情况。
        for(int i = 1; i < (1 << n); ++ i){
            List<Integer> points = new ArrayList<>();
            int curScore = 0;
            for(int j = 0; j < n; ++ j){
                if(((i >> j) & 1) == 1){
                    points.add(j);
                    curScore += score[j];
                }
            }
            // 分数不够，没必要再计算了 | 点数超过了m排除。
            if(curScore < maxScore || points.size() > m) continue;
            // 按高度，获得访问顺序。
            points.sort(Comparator.comparingInt(a -> height[a]));
            // 如果有相同高度，无效方案。
            boolean valid = true;
            for(int j = 1; j < points.size(); ++ j) if(height[points.get(j - 1)] == height[points.get(j)]){
                valid = false; break;
            }
            if(! valid) continue;
            // 选择最优的休息点。
            int lastRelaxPoint = -1;
            int startStep = 0;
            List<Integer> relaxPoints = new ArrayList<>();
            for(int j = 0; j < points.size(); ++ j) relaxPoints.add(0);
            for(int j = 0; j < points.size(); ++ j){
                int point = points.get(j);
                if(canRelax[point] == 1) lastRelaxPoint = j;
                if(j - startStep + 1 >= k){
                    // 休息点不存在或者太远了。
                    if(lastRelaxPoint == -1 || lastRelaxPoint + k <= j){
                        valid = false; break;
                    } else {
                        relaxPoints.set(lastRelaxPoint, 1);
                        startStep = lastRelaxPoint + 1;
                        lastRelaxPoint = -1;
                    }
                }
            }
            if(!valid) continue;
            if(curScore > maxScore){
                maxScore = curScore;
                ansPoints = points;
                ansRelax = relaxPoints;
            } else {
                // 分数相同，选择小的线路。
                if(compare(points, ansPoints) < 0){
                    ansPoints = points;
                    ansRelax = relaxPoints;
                }
            }
        }
        System.out.println(maxScore);
        for(int i = 0; i < ansPoints.size(); ++ i){
            System.out.print((ansPoints.get(i) + 1) + (i == ansPoints.size() - 1 ? "\n" : " "));
        }
        for(int i = 0; i < ansRelax.size(); ++ i){
            System.out.print(ansRelax.get(i) + (i == ansRelax.size() - 1 ? "\n" : " "));
        }
    }

    private static int compare(List<Integer> list1, List<Integer> list2){
        for(int i = 0; i < Math.min(list1.size(), list2.size()); ++ i){
            if(!list1.get(i).equals(list2.get(i))) return Integer.compare(list1.get(i), list2.get(i));
        }
        return Integer.compare(list1.size(), list2.size());
    }
}
