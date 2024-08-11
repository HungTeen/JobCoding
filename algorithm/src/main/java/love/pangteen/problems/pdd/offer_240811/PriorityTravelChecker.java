package love.pangteen.problems.pdd.offer_240811;

import love.pangteen.core.SolutionChecker;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/11 21:33
 **/
public class PriorityTravelChecker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(PriorityTravel.class, HQPriorityTravel.class, (input, random) -> {
            int n = random.nextInt(1, 50);
            StringBuilder sb = new StringBuilder();
            sb.append(n).append("\n");
            for (int i = 0; i < n; i++) {
                sb.append(i + 1).append(" ");
                sb.append(random.nextInt(1, 100)).append(" ");
                sb.append(random.nextInt(1, 100)).append("\n");
            }
            input.println(sb.toString());
        });
        checker.check(1000);
    }
}
