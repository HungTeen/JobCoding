package love.pangteen.concurrent.problem10;

import java.util.concurrent.Semaphore;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 21:38
 **/
public class ReaderAndWriter {

    private static final int WAIT_TIME = 0;
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore rwLock = new Semaphore(1);
    private static final Semaphore queueLock = new Semaphore(1);
    private static int readerCnt = 0;

    private static void method1(){
        new Thread(new Reader(1000)).start();
        new Thread(new Reader(1000)).start();
        new Thread(new Reader(1000)).start();
        new Thread(new Writer(1000)).start();
        new Thread(new Writer(1000)).start();
        new Thread(new Writer(1000)).start();
    }

    public static void main(String[] args) {
        method1();
    }

    private static class Reader implements Runnable {

        private final int interval;

        private Reader(int interval) {
            this.interval = interval;
        }

        @Override
        public void run() {
            while(true){
                try {
                    queueLock.acquire();
                    mutex.acquire();
                    if(readerCnt == 0){
                        rwLock.acquire();
                    }
                    ++ readerCnt;
                    mutex.release();
                    queueLock.release();

                    System.out.println(Thread.currentThread().getName() + " reading !");
                    Thread.sleep(interval);

                    mutex.acquire();
                    if(-- readerCnt == 0){
                        rwLock.release();
                    }
                    mutex.release();

                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private static class Writer implements Runnable {

        private final int interval;

        private Writer(int interval) {
            this.interval = interval;
        }

        @Override
        public void run() {
            while(true){
                try {
                    queueLock.acquire();
                    rwLock.acquire();
                    queueLock.release();

                    System.out.println(Thread.currentThread().getName() + " writing !");
                    Thread.sleep(interval);

                    rwLock.release();

                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }


}
