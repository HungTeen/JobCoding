package love.pangteen.problems.yonyou.offer_240801;

import love.pangteen.core.SolutionChecker;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/6 20:34
 **/
public class ClimbRouteChecker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(ClimbRoute.class, HQClimbRoute.class, ((printStream, random) -> {
            int n = random.nextInt(8) + 2;
            int m = random.nextInt(n) + 1;
            int r = random.nextInt(n) + 1;
            printStream.printf("%d %d %d\n", n, m, r);
            for(int i = 0; i < n; ++ i){
                printStream.print(random.nextInt(100) + (i == n - 1 ? "\n" : " "));
            }
            for(int i = 0; i < n; ++ i){
                printStream.print(random.nextInt(100) + (i == n - 1 ? "\n" : " "));
            }
            for(int i = 0; i < n; ++ i){
                printStream.print(random.nextInt(2) + (i == n - 1 ? "\n" : " "));
            }
        }));
        checker.check(1000);
    }
}
