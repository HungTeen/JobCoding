package main.java.core;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Random;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/4 13:25
 **/
public class SolutionChecker {

    private static final String PREFIX = "src/main/resources/";
    private static final String SUFFIX = ".txt";
    private final Random random;
    private final String inputName;
    private final String currentOutputName;
    private final String standardOutputName;
    private final Class<?> currentSolution;
    private final Class<?> standardSolution;
    private final InputGenerator inputGenerator;

    public SolutionChecker(Class<?> currentSolution, Class<?> standardSolution, InputGenerator inputGenerator){
        this(currentSolution, standardSolution, "input", "output", inputGenerator);
    }

    public SolutionChecker(Class<?> currentSolution, Class<?> standardSolution, String inputName, String outputName, InputGenerator inputGenerator) {
        this.random = new Random(Calendar.getInstance().getTimeInMillis());
        this.currentSolution = currentSolution;
        this.standardSolution = standardSolution;
        this.inputName = inputName;
        this.currentOutputName = "current_" + outputName;
        this.standardOutputName = "standard_" + outputName;
        this.inputGenerator = inputGenerator;
    }

    public void check(int times){
        for (int i = 0; i < times; i++) {
            System.out.println("第" + (i + 1) + "次测试");
            if(! check()){
                break;
            }
        }
        System.out.println("测试完成！");
    }

    private boolean check(){
        // 随机生成输入。
        try {
            PrintStream outputStream = new PrintStream(path(inputName));
            this.inputGenerator.generateInput(outputStream, random);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.print("开始运行当前方法 ----> ");
        runSolution(false);
        System.out.print("开始运行标准方法 ----> ");
        runSolution(true);
        System.out.print("开始进行比较 ----> ");
        try {
            boolean same = Files.mismatch(new File(path(standardOutputName)).toPath(), new File(path(currentOutputName)).toPath()) == -1;
            System.out.println(same ? "答案正确！" : "答案错误！");
            return same;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void runSolution(boolean isStandard){
        PrintStream consoleOutput = System.out;
        // 设置输入为文件输入。
        try {
            System.setIn(new FileInputStream(path(inputName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 设置当前答案的输出文件。
        try {
            System.setOut(new PrintStream(path(isStandard ? standardOutputName : currentOutputName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 跑当前答案。
        try {
            Class<?> solutionClass = isStandard ? standardSolution : currentSolution;
            Method mainMethod = solutionClass.getMethod("main", String[].class);
            long startTime = System.nanoTime();
            mainMethod.invoke(null, (Object) new String[0]);
            long endTime = System.nanoTime();
            consoleOutput.println("运行时间：" + (endTime - startTime) / 1000000 + "ms");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        System.setOut(consoleOutput);
    }

    private String path(String name){
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX).append(name).append(SUFFIX);
        return sb.toString();
    }

}
