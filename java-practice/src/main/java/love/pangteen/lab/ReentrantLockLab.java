package love.pangteen.lab;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/26 17:05
 **/
public class ReentrantLockLab {

    /**
     * 不太理解 AQS 对公平锁和非公平锁的实现，这里通过代码来验证一下。
     */
    public static void main(String[] args) {
        fairOrUnfair(false);
    }

    private static void fairOrUnfair(boolean fair){
        ReentrantLock lock = new ReentrantLock(fair);
        // 1个成功，9个进入队列。
        for(int i = 0; i < 10; ++ i){
            new Thread(() -> {
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    System.out.println("queue length: " + lock.getQueueLength());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 10; ++ i){
            new Thread(() -> {
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    System.out.println("queue length: " + lock.getQueueLength());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }, "Neo Thread-" + (i + 100)).start();
        }
    }
}
