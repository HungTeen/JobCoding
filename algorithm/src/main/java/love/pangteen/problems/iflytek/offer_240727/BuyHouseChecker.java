package love.pangteen.problems.iflytek.offer_240727;

import love.pangteen.core.SolutionChecker;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/29 22:56
 **/
public class BuyHouseChecker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(BuyHouse.class, HQBuyHouse.class, ((printStream, random) -> {
            int n = random.nextInt(50) + 1;
            int m = random.nextInt(80) + 1;
            printStream.printf("%d %d\n", n, m);
            for(int i = 0; i < n; ++ i){
                printStream.printf("%d ", random.nextInt(1000) + 1);
            }
            printStream.print("\n");
            for(int i = 0; i < m; ++ i){
                printStream.printf("%d %d\n", random.nextInt(1000) + 1, random.nextInt(1000) + 1);
            }
        }));
        checker.check(10000);
    }
}
