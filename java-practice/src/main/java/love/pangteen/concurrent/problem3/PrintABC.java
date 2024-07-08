package love.pangteen.concurrent.problem3;

import java.util.concurrent.Semaphore;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 10:32
 **/
public class PrintABC {

    private static final int n = 1000;

    /**
     * 三个信号量同步。
     */
    private static void method1(){
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < n; ++ i){
                try {
                    a.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": A");
                b.release();
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < n; ++ i){
                try {
                    b.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": B");
                c.release();
            }
        });
        Thread thread3 = new Thread(() -> {
            for(int i = 0; i < n; ++ i){
                try {
                    c.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": C");
                a.release();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        method1();
//        method2();
        long end = System.currentTimeMillis();
        System.out.println("Finish in " + (end - start) + "ms");
    }
}
