package main.java.problems.kuaishou.internship_240508;

import main.java.core.SolutionChecker;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/10 00:17
 **/
public class OverlapChecker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(Overlap.class, STDOverlap.class, ((printStream, random) -> {
            int n = random.nextInt(5);
            printStream.printf("%d\n", n);
            for(int i = 0; i < n; ++ i){
                int x1 = random.nextInt(5);
                int y1 = random.nextInt(5);
                int x2 = x1 + 1 + random.nextInt(5);
                int y2 = y1 + 1 + random.nextInt(5);
                printStream.printf("%d %d %d %d\n", x1, y1, x2, y2);
            }
        }));
        checker.check(1000);
    }
}
