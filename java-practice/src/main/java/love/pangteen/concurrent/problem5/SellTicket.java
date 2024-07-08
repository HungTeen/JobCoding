package love.pangteen.concurrent.problem5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 15:11
 **/
public class SellTicket {

    private static final int SELLER = 3;
    private static final int TICKET_NUM = 1000;
    private static final Object o = new Object();
    private static volatile int tickets = TICKET_NUM;

    /**
     * 使用synchronized加锁维护全局ticket。
     */
    private static void method1(){
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < SELLER; ++ i){
            Thread thread = new Thread(() -> {
                Random random = new Random(System.currentTimeMillis());
                while (true) {
                    if (random.nextFloat() < 0.05F) {
                        synchronized (o){
                            if(tickets > 0){
                                -- tickets;
                                System.out.println(Thread.currentThread().getName() + " : left " + tickets);
                            } else break;
                        }
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 使用信号量来维护票数。
     */
    private static void method2(){
        Semaphore ticket = new Semaphore(TICKET_NUM);
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < SELLER; ++ i){
            Thread thread = new Thread(() -> {
                Random random = new Random(System.currentTimeMillis());
                while (true) {
                    if (random.nextFloat() < 0.05F) {
                        if (ticket.tryAcquire()) {
                            System.out.println(Thread.currentThread().getName() + " : left " + ticket.availablePermits());
                        } else break;
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
