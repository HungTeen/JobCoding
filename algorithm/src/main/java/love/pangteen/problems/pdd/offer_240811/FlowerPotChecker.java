package love.pangteen.problems.pdd.offer_240811;

import love.pangteen.core.SolutionChecker;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/11 22:21
 **/
public class FlowerPotChecker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(FlowerPot.class, HQFlowerPot.class, ((printStream, random) -> {
            int n = random.nextInt(1, 10);
            printStream.println(n);
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(2);
                printStream.print(x + " ");
            }
            printStream.println();
        }));
        checker.check(10000);
    }
}
