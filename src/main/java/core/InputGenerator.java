package main.java.core;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 13:36
 **/
@FunctionalInterface
public interface InputGenerator {

    void generateInput(PrintStream printStream, Random random);

}
