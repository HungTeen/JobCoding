package love.pangteen.concurrent.problem1;

import java.util.concurrent.CompletableFuture;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 9:02
 **/
public class ThreadOneByOne {

    /**
     * 一般方法，join() 可以等待线程执行完毕。
     */
    private static void method1(){
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用CompletableFuture对线程进行编排。
     */
    private static void method2(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Thread 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRunAsync(() -> {
            System.out.println("Thread 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRunAsync(() -> {
            System.out.println("Thread 3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        future.join();
    }

    public static void main(String[] args) {
//        method1();
        method2();
        System.out.println("Finish");
    }
}
