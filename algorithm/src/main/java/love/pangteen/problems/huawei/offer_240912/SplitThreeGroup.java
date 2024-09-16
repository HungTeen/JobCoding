package love.pangteen.problems.huawei.offer_240912;

public class SplitThreeGroup {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solve(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}));
        System.out.println(solution.solve(new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1}));
    }

    public static class Solution {

        public boolean solve(int[] f){
            long sum = 0;
            int n = f.length;
            for(int i = n - 1; i >= 0; -- i){
                sum += f[i];
            }
            if(sum % 3 != 0){
                return false;
            }
            long x = sum / 3;
            int lPos = 0;
            long preSum = 0;
            while(lPos < n && preSum != x){
                preSum += f[lPos];
                ++ lPos;
            }
            if(lPos == n){
                return false;
            }

            int rPos = n - 1;
            long sufSum = 0;
            while(rPos > lPos && sufSum != x){
                sufSum += f[rPos];
                rPos --;
            }

            if(lPos == rPos) return false;
            return preSum == sufSum;
        }
    }


}