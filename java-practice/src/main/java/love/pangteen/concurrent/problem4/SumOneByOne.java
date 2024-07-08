package love.pangteen.concurrent.problem4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 11:01
 **/
public class SumOneByOne {

    private static final int THREAD_CNT = 10;
    private static final int EACH_ADD_CNT = 100;
    private static final AtomicInteger num = new AtomicInteger(0);
    private static final AtomicLong result = new AtomicLong(0);

    /**
     * CompletableFuture串行执行。
     */
    private static void method1(){
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> 0L);
        for(int i = 0; i < THREAD_CNT; ++ i){
            future = future.thenApplyAsync((t) -> {
                long sum = t;
                for(int j = 0; j < EACH_ADD_CNT; ++ j){
                    int now = num.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " -> " + sum + " + " + now);
                    sum += now;
                }
                return sum;
            });
        }
        Long result = future.join();
        System.out.println(result);
    }

    /**
     * Thread List 异步执行，但是乐观锁频繁修改性能不好。
     */
    private static void method2(){
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < THREAD_CNT; ++ i){
            final int start = i * EACH_ADD_CNT;
            Thread thread = new Thread(() -> {
                for (int j = 1; j <= EACH_ADD_CNT; ++j) {
                    int now = start + j;
                    System.out.println(Thread.currentThread().getName() + " -> " + result.get() + " + " + now + " = " + result.addAndGet(now));
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(result.get());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        method1();
        method2();
        long end = System.currentTimeMillis();
        System.out.println("Finish in " + (end - start) + "ms");
    }
}
