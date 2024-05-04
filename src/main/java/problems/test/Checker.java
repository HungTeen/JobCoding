package main.java.problems.test;

import main.java.core.SolutionChecker;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 15:01
 **/
public class Checker {

    public static void main(String[] args) {
        SolutionChecker checker = new SolutionChecker(MySolution.class, STDSolution.class, ((printStream, random) -> {
            printStream.printf("%d %d\n", random.nextInt(100), random.nextInt(1000));
        }));
        checker.check(10);
    }
}
