package love.pangteen.concurrent.problem8;

import java.util.concurrent.Semaphore;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 21:37
 **/
public class ReaderFirst {

    private static final int READER_RELAX_TIME = 10000;
    private static int readerCnt = 0;
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore readLock = new Semaphore(1);

    private static void method1(){
        new Thread(new Reader(1000)).start();
        new Thread(new Reader(2000)).start();
        new Thread(new Reader(3000)).start();
        new Thread(new Reader(4000)).start();
        new Thread(new Writer()).start();
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
            while (true){
                try {
                    mutex.acquire();
                    System.out.println(Thread.currentThread().getName() + " try to read.");
                    if(readerCnt == 0){
                        readLock.acquire();
                    }
                    ++ readerCnt;
                    mutex.release();

                    System.out.println(Thread.currentThread().getName() + " Reading !");
                    Thread.sleep(interval);

                    mutex.acquire();
                    System.out.println(Thread.currentThread().getName() + " quit.");
                    if(-- readerCnt == 0){
                        readLock.release();
                    }
                    mutex.release();

                    Thread.sleep(READER_RELAX_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class Writer implements Runnable {

        @Override
        public void run() {
            while (true){
                try {
                    readLock.acquire();

                    System.out.println("Start writing !");
                    Thread.sleep(3000);
                    System.out.println("Stop writing !");

                    readLock.release();

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
