package love.pangteen.concurrent.problem7;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 21:27
 **/
public class DeadLock {

    private static void method1(){
        Object x = new Object();
        Object y = new Object();
        new Thread(() -> {
            synchronized (x){
                System.out.println(Thread.currentThread().getName() + " entered X");
                try {
                    Thread.sleep(2000);
                    synchronized (y){
                        System.out.println(Thread.currentThread().getName() + " entered Y");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        new Thread(() -> {
            synchronized (y){
                System.out.println(Thread.currentThread().getName() + " entered Y");
                try {
                    Thread.sleep(2000);
                    synchronized (x){
                        System.out.println(Thread.currentThread().getName() + " entered X");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    public static void main(String[] args) {
        method1();
    }
}
