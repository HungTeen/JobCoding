package love.pangteen.concurrent.problem9;

import java.util.concurrent.Semaphore;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 21:38
 **/
public class WriterFirst {

    private static final int READER_RELAX_TIME = 10000;
    private static final int WRITER_RELAX_TIME = 8000;
    private static final Semaphore readerMutex = new Semaphore(1);
    private static final Semaphore writerMutex = new Semaphore(1);
    private static final Semaphore rwLock = new Semaphore(1);
    private static final Semaphore queueLock = new Semaphore(1);
    private static int readerCnt = 0;
    private static int writerCnt = 0;

    private static void method1(){
        new Thread(new Reader(1000)).start();
        new Thread(new Reader(2000)).start();
        new Thread(new Reader(3000)).start();
        new Thread(new Reader(4000)).start();
        new Thread(new Writer(500)).start();
        new Thread(new Writer(1500)).start();
        new Thread(new Writer(3000)).start();
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
                    queueLock.acquire();
                    readerMutex.acquire();
                    System.out.println(Thread.currentThread().getName() + " try to read.");
                    if(readerCnt == 0){
                        rwLock.acquire();
                    }
                    ++ readerCnt;
                    readerMutex.release();
                    queueLock.release();

                    System.out.println(Thread.currentThread().getName() + " Reading !");
                    Thread.sleep(interval);

                    readerMutex.acquire();
                    System.out.println(Thread.currentThread().getName() + " reader quit.");
                    if(-- readerCnt == 0){
                        rwLock.release();
                    }
                    readerMutex.release();

                    Thread.sleep(READER_RELAX_TIME);
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
            while (true){
                try {

                    writerMutex.acquire();
                    System.out.println(Thread.currentThread().getName() + " try to write.");
                    if(writerCnt == 0){
                        queueLock.acquire();
                    }
                    ++ writerCnt;
                    writerMutex.release();

                    rwLock.acquire();

                    System.out.println("Start writing !");
                    Thread.sleep(interval);
                    System.out.println("Stop writing !");

                    writerMutex.acquire();
                    System.out.println(Thread.currentThread().getName() + " writer quit.");
                    rwLock.release();
                    if(-- writerCnt == 0){
                        queueLock.release();
                    }
                    writerMutex.release();

                    Thread.sleep(WRITER_RELAX_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
