package love.pangteen.concurrent.problem2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 9:13
 **/
public class OddAndEven {

    private static final int n = 10000;
    private static final AtomicInteger cnt = new AtomicInteger(1);
    private static final Object o = new Object();

    /**
     * 使用synchronize加锁。
     */
    private static void method1(){
        Thread thread1 = new Thread(() -> {
            while(cnt.get() <= n){
                synchronized (o){
                    if (cnt.get() <= n && (cnt.get() & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + cnt);
                        cnt.incrementAndGet();
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while(cnt.get() <= n){
                synchronized (o){
                    if (cnt.get() <= n && (cnt.get() & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + cnt);
                        cnt.incrementAndGet();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用同步队列。
     */
    private static void method2(){
        SynchronousQueue<Integer> q = new SynchronousQueue<>();
        Thread thread1 = new Thread(() -> {
            int val = 1;
            while (val <= n) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + val);
                    q.put(val + 1);
                    val = q.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            int val = 1;
            while (val < n) {
                try {
                    val = q.take();
                    System.out.println(Thread.currentThread().getName() + ": " + val);
                    q.put(val + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 两个信号量。
     */
    private static void method3(){
        Semaphore odd = new Semaphore(1);
        Semaphore even = new Semaphore(0);
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= n; i += 2) {
                try {
                    odd.acquire();
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    even.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 2; i <= n; i += 2) {
                try {
                    even.acquire();
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    odd.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用ReentrantLock+Condition。
     */
    private static void method4(){
        ReentrantLock lock = new ReentrantLock();
        Condition odd = lock.newCondition();
        Condition even = lock.newCondition();
        Thread thread1 = new Thread(() -> {
            for(int i = 1; i <= n; i += 2){
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    even.signal();
                    if(i + 2 <= n) odd.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 2; i <= n; i += 2){
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    odd.signal();
                    if(i + 2 <= n) even.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        method1();
//        method2();
//        method3();
        method4();
        long end = System.currentTimeMillis();
        System.out.println("Finish in " + (end - start) + "ms");
    }

}
