package main.java.problems.alibaba.internship_240506;

import main.java.core.SolutionChecker;
import main.java.problems.kuaishou.internship_240508.BadOverlap;
import main.java.problems.kuaishou.internship_240508.BestOverlap;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/12 19:17
 **/
public class XorSequenceChecker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(XorSequence.class, STDXorSequence.class, ((printStream, random) -> {
            int n = random.nextInt(20) + 1;
            printStream.printf("%d\n", n);
            for(int i = 0; i < n; ++ i){
                printStream.print((random.nextInt(10000000) + 1) + (i == n - 1 ? "\n" : " "));
            }
        }));
        checker.check(1000);
    }
}
